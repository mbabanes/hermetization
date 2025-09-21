package btn.jmt.hermetization.controller.internal.model;

import btn.jmt.hermetization.service.document.dto.DocumentDetails;
import btn.jmt.hermetization.service.document.dto.DocumentType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.PACKAGE)
public class DocumentResource {
  private String documentId;
  private String fileName;
  private DocumentTypeResource documentType;

  public static DocumentResource from(DocumentDetails documentDetails) {
    var document = new DocumentResource();
    document.setDocumentId(documentDetails.documentId().value());
    document.setDocumentType(map(documentDetails.documentType()));
    document.setFileName(documentDetails.name());
    return document;
  }

  private static DocumentTypeResource map(
      DocumentType documentType) {
    return switch (documentType) {
      case ATTACHMENT -> DocumentTypeResource.ATTACHMENT;
      case INFO -> DocumentTypeResource.INFO;
    };
  }
}
