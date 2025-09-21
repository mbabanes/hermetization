package btn.jmt.hermetization.service.document;

import btn.jmt.hermetization.service.document.dto.DocumentDetails;
import btn.jmt.hermetization.service.document.dto.DocumentFile;
import btn.jmt.hermetization.service.document.dto.DocumentId;
import btn.jmt.hermetization.service.document.dto.DocumentUploadingStatus;
import btn.jmt.hermetization.service.process.dto.ProcessNumber;
import java.util.List;

public interface DocumentService2 {
  DocumentUploadingStatus saveAttachmentDocument(
      String processNumber, String documentFile);

  DocumentFile downloadDocument(String processNumber, String documentId);

  List<DocumentDetails> findAllDocuments(String processNumber);

  void signDocument(String processNumber, String documentId);
}


class DocService2Impl implements DocumentService2 {
  @Override
  public DocumentUploadingStatus saveAttachmentDocument(String processNumber, String documentFile) {
    return null;
  }

  @Override
  public DocumentFile downloadDocument(String processNumber, String documentId) {
    return null;
  }

  @Override
  public List<DocumentDetails> findAllDocuments(String processNumber) {
    return List.of();
  }

  @Override
  public void signDocument(String processNumber, String documentId) {

  }
}