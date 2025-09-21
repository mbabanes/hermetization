package btn.jmt.hermetization.service.document;

import btn.jmt.hermetization.service.document.dto.DocumentDetails;
import btn.jmt.hermetization.service.document.dto.DocumentFile;
import btn.jmt.hermetization.service.document.dto.DocumentId;
import btn.jmt.hermetization.service.document.dto.DocumentUploadingStatus;
import btn.jmt.hermetization.service.process.dto.ProcessNumber;
import java.util.List;

public interface DocumentService {
  DocumentUploadingStatus saveAttachmentDocument(
      ProcessNumber processNumber, DocumentFile documentFile);

  DocumentFile downloadDocument(ProcessNumber processNumber, DocumentId documentId);

  List<DocumentDetails> findAllDocuments(ProcessNumber processNumber);

  void signDocument(ProcessNumber processNumber, DocumentId documentId);
}
