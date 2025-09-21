package btn.jmt.hermetization.service.document;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import btn.jmt.hermetization.service.document.dto.DocumentFile;
import btn.jmt.hermetization.service.document.dto.DocumentUploadingStatus;
import btn.jmt.hermetization.service.process.dto.ProcessNumber;
import org.junit.jupiter.api.Test;

class DocumentUploadingValidatorTest {

  private final DocumentProperties documentProperties = new DocumentProperties();
  private final DocumentRepository documentRepository = mock(DocumentRepository.class);

  private final DocumentUploadingValidator documentUploadingValidator =
      new DocumentUploadingValidator(documentRepository, documentProperties);

  @Test
  void shouldReturnSuccess() {
    // given
    var documentFile = new DocumentFile(new byte[] {}, "file.pdf");
    var processNumber = new ProcessNumber("pn");
    when(documentRepository.countByProcessNumber(processNumber.value()))
        .thenReturn(Long.valueOf(documentProperties.getUploadingFile().maxFileNumber() - 1));

    // when
    DocumentUploadingValidator.Result validationResult =
        documentUploadingValidator.validate(processNumber, documentFile);

    // then
    assertThat(validationResult.canUpload()).isTrue();
    assertThat(validationResult.status()).isEqualTo(DocumentUploadingStatus.SUCCESS);
  }

  @Test
  void shouldReturnNotAllowedFileExtensionStatus() {
    // given
    var documentFile = new DocumentFile(new byte[] {}, "file.txt");
    var processNumber = new ProcessNumber("pn");
    when(documentRepository.countByProcessNumber(processNumber.value()))
        .thenReturn(Long.valueOf(documentProperties.getUploadingFile().maxFileNumber()));
    // when
    DocumentUploadingValidator.Result validationResult =
        documentUploadingValidator.validate(processNumber, documentFile);

    // then
    assertThat(validationResult.canUpload()).isFalse();
    assertThat(validationResult.status()).isEqualTo(DocumentUploadingStatus.NOT_ALLOWED_EXTENSION);
  }

  @Test
  void shouldReturnTooManyFileUploaded() {
    // given
    var documentFile = new DocumentFile(new byte[] {}, "file.pdf");
    var processNumber = new ProcessNumber("pn");
    when(documentRepository.countByProcessNumber(processNumber.value()))
        .thenReturn(Long.valueOf(documentProperties.getUploadingFile().maxFileNumber()));

    // when
    DocumentUploadingValidator.Result validationResult =
        documentUploadingValidator.validate(processNumber, documentFile);

    // then
    assertThat(validationResult.canUpload()).isFalse();
    assertThat(validationResult.status()).isEqualTo(DocumentUploadingStatus.TOO_MANY_FILE_UPLOADED);
  }
}
