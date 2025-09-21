package btn.jmt.hermetization.controller.internal;

import btn.jmt.hermetization.controller.internal.model.DocumentResource;
import btn.jmt.hermetization.controller.internal.model.DocumentTypeResource;
import btn.jmt.hermetization.service.document.DocumentService;
import btn.jmt.hermetization.service.document.dto.DocumentDetails;
import btn.jmt.hermetization.service.document.dto.DocumentId;
import btn.jmt.hermetization.service.process.dto.ProcessNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
class DocumentController {

  private final DocumentService documentService;

  @PutMapping("{processNumber}/documents/{documentId}/signature")
  void putSignature(@PathVariable String processNumber, @PathVariable String documentId) {
    documentService.signDocument(new ProcessNumber(processNumber), new DocumentId(documentId));
  }

  @GetMapping("{processNumber}/documents/")
  List<DocumentResource> getDocumentBy(@PathVariable String processNumber) {
    return documentService.findAllDocuments(new ProcessNumber(processNumber)).stream()
        .map(DocumentResource::from)
        .toList();
  }
}
