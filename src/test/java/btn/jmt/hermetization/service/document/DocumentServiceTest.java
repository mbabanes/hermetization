package btn.jmt.hermetization.service.document;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import btn.jmt.hermetization.service.document.dto.*;
import btn.jmt.hermetization.service.process.ProcessService;
import btn.jmt.hermetization.service.process.dto.PhoneNumber;
import btn.jmt.hermetization.service.process.dto.ProcessNumber;
import btn.jmt.hermetization.service.process.dto.ProcessState;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

class DocumentServiceTest {

  private final DocumentRepository documentRepository = mock(DocumentRepository.class);
  private final FileExternalClient fileExternalClient = mock(FileExternalClient.class);
  private final PdfSignature pdfSignature = mock(PdfSignature.class);
  private final ProcessService processService = mock(ProcessService.class);
  private final DocumentEmailSender documentEmailSender = mock(DocumentEmailSender.class);
  private final DocumentUploadingValidator documentUploadingValidator =
      mock(DocumentUploadingValidator.class);

  private final DocumentService documentService =
      new DocumentServiceImpl(
          documentRepository,
          fileExternalClient,
          pdfSignature,
          processService,
          documentEmailSender,
          documentUploadingValidator);

  @Test
  void shouldSaveDocumentWhenFileValidationIsSuccess() {
    // given
    var processNumber = new ProcessNumber("pn");
    var documentFile = new DocumentFile(new byte[] {}, "filename.pdf");
    var validationResult =
        new DocumentUploadingValidator.Result(DocumentUploadingStatus.SUCCESS, true);
    when(documentUploadingValidator.validate(processNumber, documentFile))
        .thenReturn(validationResult);
    var barcode = new DocumentBarcode("barcode");
    when(fileExternalClient.uploadFile(documentFile, processNumber)).thenReturn(barcode);

    // when
    DocumentUploadingStatus savingDocumentResult =
        documentService.saveAttachmentDocument(processNumber, documentFile);

    // then
    assertThat(savingDocumentResult).isEqualTo(DocumentUploadingStatus.SUCCESS);
    verify(documentRepository)
        .save(
            assertArg(
                savedDocument -> {
                  assertThat(savedDocument.getId()).isNotEmpty();
                  assertThat(savedDocument.getDocumentType()).isEqualTo(DocumentType.ATTACHMENT);
                  assertThat(savedDocument.getBarcode()).isEqualTo(barcode.value());
                  assertThat(savedDocument.getName()).isEqualTo(documentFile.fileName());
                  assertThat(savedDocument.getProcessNumber()).isEqualTo(processNumber.value());
                }));
  }

  @Test
  void shouldSignDocumentAndSendItToClient() {
    // given
    var documentBarcode = new DocumentBarcode("barcode");
    var processNumber = new ProcessNumber("pn");
    DocumentEntity document =
        DocumentEntity.createNew(
            documentBarcode, processNumber, DocumentType.ATTACHMENT, "docName");
    var documentId = new DocumentId(document.getId());

    when(documentRepository.findByIdAndProcessNumber(documentId.value(), processNumber.value()))
        .thenReturn(Optional.of(document));

    var documentFile = new DocumentFile(new byte[] {}, document.getName());
    when(fileExternalClient.downloadDocument(documentBarcode)).thenReturn(documentFile);

    var signedDocument = new DocumentFile(new byte[] {}, documentFile.fileName());
    var phoneNumber = new PhoneNumber("+48111222333");
    when(processService.findPhoneNumber(processNumber)).thenReturn(phoneNumber);
    when(pdfSignature.putSignatureToDocument(
            eq(documentFile), eq(phoneNumber), any(LocalDateTime.class)))
        .thenReturn(signedDocument);

    // when
    documentService.signDocument(processNumber, documentId);

    // then
    verify(fileExternalClient).updateFile(documentBarcode, signedDocument);

    var signatureDateTimeArgCaptor = ArgumentCaptor.forClass(LocalDateTime.class);
    verify(processService)
        .updateDocumentSignatureDateTime(eq(processNumber), signatureDateTimeArgCaptor.capture());
    assertThat(signatureDateTimeArgCaptor.getValue())
        .isCloseTo(LocalDateTime.now(), within(30, ChronoUnit.SECONDS));

    verify(processService).updateProcessState(processNumber, ProcessState.SUMMARY);
    verify(documentEmailSender).sendEmailToClient(processNumber, signedDocument);
  }
}
