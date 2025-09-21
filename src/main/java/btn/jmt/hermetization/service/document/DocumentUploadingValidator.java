package btn.jmt.hermetization.service.document;

import btn.jmt.hermetization.service.document.dto.DocumentFile;
import btn.jmt.hermetization.service.document.dto.DocumentUploadingStatus;
import btn.jmt.hermetization.service.process.dto.ProcessNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class DocumentUploadingValidator {

  private final DocumentRepository documentRepository;
  private final DocumentProperties documentProperties;

  record Result(DocumentUploadingStatus status, boolean canUpload) {
    private static Result fail(DocumentUploadingStatus status) {
      return new Result(status, false);
    }

    private static Result success() {
      return new Result(DocumentUploadingStatus.SUCCESS, true);
    }
  }

  Result validate(ProcessNumber processNumber, DocumentFile documentFile) {
    log.debug("Validation uploading document {}", processNumber);

    if (isNotAllowedFileExtension(documentFile)) {
      log.debug(
          "Uploading file of {} has wrong extension allowed extensions are {}",
          processNumber,
          documentProperties.getUploadingFile().allowedExtensions());
      return Result.fail(DocumentUploadingStatus.NOT_ALLOWED_EXTENSION);
    }

    if (isUploadedFileNumberExceeded(processNumber)) {
      log.debug("Too much files have been uploaded of {}", processNumber.value());
      return Result.fail(DocumentUploadingStatus.TOO_MANY_FILE_UPLOADED);
    }

    return Result.success();
  }

  private boolean isNotAllowedFileExtension(DocumentFile documentFile) {
    return documentProperties.getUploadingFile().allowedExtensions().stream()
        .noneMatch(extension -> StringUtils.endsWith(documentFile.fileName(), extension));
  }

  private boolean isUploadedFileNumberExceeded(ProcessNumber processNumber) {
    return documentRepository.countByProcessNumber(processNumber.value())
        == documentProperties.getUploadingFile().maxFileNumber();
  }
}
