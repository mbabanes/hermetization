package btn.jmt.hermetization.service.document;

import btn.jmt.hermetization.service.common.AuditableEntity;
import btn.jmt.hermetization.service.document.dto.DocumentBarcode;
import btn.jmt.hermetization.service.document.dto.DocumentType;
import btn.jmt.hermetization.service.process.dto.ProcessNumber;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.*;

@Entity
@NoArgsConstructor
@Setter(AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
class DocumentEntity extends AuditableEntity {

  @Id private String id;

  private String barcode;
  private String name;
  private String processNumber;

  @Enumerated(EnumType.STRING)
  private DocumentType documentType;

  static DocumentEntity createNew(
      DocumentBarcode documentBarcode,
      ProcessNumber processNumber,
      DocumentType documentType,
      String name) {
    final DocumentEntity documentEntity = new DocumentEntity();
    documentEntity.setId(UUID.randomUUID().toString());
    documentEntity.setProcessNumber(processNumber.value());
    documentEntity.setBarcode(documentBarcode.value());
    documentEntity.setName(name);
    documentEntity.setDocumentType(documentType);
    return documentEntity;
  }
}
