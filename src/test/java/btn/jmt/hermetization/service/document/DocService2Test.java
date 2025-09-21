package btn.jmt.hermetization.service.document;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import btn.jmt.hermetization.service.document.dto.DocumentFile;
import btn.jmt.hermetization.service.document.dto.DocumentId;
import btn.jmt.hermetization.service.process.dto.ProcessNumber;
import org.junit.jupiter.api.Test;

class DocService2Test {

  private final DocumentService2 documentService2 = new DocService2Impl();
  private final DocumentService2 mockDocumentService2 = mock(DocumentService2.class);
  private final DocumentService mockDocumentService = mock(DocumentService.class);

  @Test
  void shouldDoSomething() {
    // given
    final String processNumber = "CO2321123550";
    final String documentId = "22321aada";
    documentService2.downloadDocument(processNumber, documentId);
    // when

    // then
  }

  @Test
  void shouldDoSomething2() {
    // given
    when(mockDocumentService2.downloadDocument(anyString(), anyString()))
        .thenReturn(createDocumentFile());

    when(mockDocumentService.downloadDocument(any(ProcessNumber.class), any(DocumentId.class)))
        .thenReturn(createDocumentFile());

    // when

    // then
  }

  private static DocumentFile createDocumentFile() {
    return new DocumentFile(new byte[]{}, "fName");
  }
}
