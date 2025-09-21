package btn.jmt.hermetization.service.document;

import btn.jmt.hermetization.service.document.dto.*;
import btn.jmt.hermetization.service.document.exception.DocumentFileNotFoundException;
import btn.jmt.hermetization.service.process.ProcessService;
import btn.jmt.hermetization.service.process.dto.ProcessNumber;
import btn.jmt.hermetization.service.process.dto.ProcessState;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class DocumentServiceImpl implements DocumentService {

  private final DocumentRepository documentRepository;
  private final FileExternalClient fileExternalClient;
  private final PdfSignature pdfSignature;
  private final ProcessService processService;
  private final DocumentEmailSender documentEmailSender;
  private final DocumentUploadingValidator documentUploadingValidator;

  @Override
  public DocumentUploadingStatus saveAttachmentDocument(
          ProcessNumber processNumber, DocumentFile documentFile) {
    log.info("Saving attachment document of {}", processNumber);

    DocumentUploadingValidator.Result validationResult = documentUploadingValidator.validate(processNumber, documentFile);
    if (validationResult.canUpload()) {
      final DocumentBarcode documentBarcode =
          fileExternalClient.uploadFile(documentFile, processNumber);
      final var document =
          DocumentEntity.createNew(
              documentBarcode, processNumber, DocumentType.ATTACHMENT, documentFile.fileName());
      documentRepository.save(document);
    }

    return validationResult.status();
  }

  @Override
  public DocumentFile downloadDocument(ProcessNumber processNumber, DocumentId documentId) {
    log.info("Downloading file of {} {}", processNumber, documentId);
    return documentRepository
        .findByIdAndProcessNumber(documentId.value(), processNumber.value())
        .map(this::downloadFile)
        .orElseThrow(() -> DocumentFileNotFoundException.of(processNumber, documentId));
  }

  @Override
  public List<DocumentDetails> findAllDocuments(ProcessNumber processNumber) {
    log.info("Finding documents of {}", processNumber);

    return documentRepository.findAllByProcessNumber(processNumber.value()).stream()
        .map(this::toDocumentDetails)
        .toList();
  }

  @Override
  public void signDocument(ProcessNumber processNumber, DocumentId documentId) {
    log.info("Signing document {} of {}", documentId, processNumber);
    DocumentEntity document =
        documentRepository
            .findByIdAndProcessNumber(documentId.value(), processNumber.value())
            .orElseThrow(() -> DocumentFileNotFoundException.of(processNumber, documentId));

    final DocumentFile documentFile = this.downloadFile(document);
    final var signatureDateTime = LocalDateTime.now();
    final DocumentFile documentFileWithSignature =
        pdfSignature.putSignatureToDocument(
            documentFile, processService.findPhoneNumber(processNumber), signatureDateTime);
    fileExternalClient.updateFile(
        new DocumentBarcode(document.getBarcode()), documentFileWithSignature);
    processService.updateDocumentSignatureDateTime(processNumber, signatureDateTime);
    processService.updateProcessState(processNumber, ProcessState.SUMMARY);
    documentEmailSender.sendEmailToClient(processNumber, documentFileWithSignature);
  }

  private DocumentDetails toDocumentDetails(DocumentEntity doc) {
    return new DocumentDetails(
        new DocumentId(doc.getId()),
        new DocumentBarcode(doc.getBarcode()),
        doc.getName(),
        doc.getDocumentType());
  }

  private DocumentFile downloadFile(DocumentEntity doc) {
    final DocumentFile documentFile =
        fileExternalClient.downloadDocument(new DocumentBarcode(doc.getBarcode()));
    return new DocumentFile(documentFile.bytes(), doc.getName());
  }
}
