package btn.jmt.hermetization.service.document.exception;

import btn.jmt.hermetization.exception.NotFoundException;
import btn.jmt.hermetization.service.document.dto.DocumentId;
import btn.jmt.hermetization.service.process.dto.ProcessNumber;

public class DocumentFileNotFoundException extends NotFoundException {

  private DocumentFileNotFoundException(ProcessNumber processNumber, String message) {
    super(processNumber, message);
  }

  public static DocumentFileNotFoundException of(ProcessNumber processNumber, DocumentId documentId) {
    return new DocumentFileNotFoundException(
        processNumber, String.format("Cant find document of %s %s", processNumber, documentId));
  }
}
