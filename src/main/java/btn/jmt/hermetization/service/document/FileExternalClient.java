package btn.jmt.hermetization.service.document;

import btn.jmt.hermetization.generated.fileservice2.client.FilesApi;
import btn.jmt.hermetization.generated.fileservice2.model.FileCreatingRequestFsExt;
import btn.jmt.hermetization.generated.fileservice2.model.UpdatingFileRequestFsExt;
import btn.jmt.hermetization.service.document.dto.DocumentBarcode;
import btn.jmt.hermetization.service.document.dto.DocumentFile;
import btn.jmt.hermetization.service.process.dto.ProcessNumber;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class FileExternalClient {

  private static final int NEW_FILE_TYPE = 2;
  private final FilesApi fileServiceApi;

  DocumentBarcode uploadFile(DocumentFile documentFile, ProcessNumber processNumber) {

    final var response =
        fileServiceApi.createFile(
            new FileCreatingRequestFsExt()
                ._file(Base64.getEncoder().encodeToString(documentFile.bytes()))
                .applicationId(processNumber.value())
                .type(NEW_FILE_TYPE)
                .data01("new-file"));

    return new DocumentBarcode(response.getBarcode());
  }

  DocumentFile downloadDocument(DocumentBarcode documentBarcode) {
    final var response = fileServiceApi.getFileByBarcode(documentBarcode.value());
    return DocumentFile.of(
        Base64.getDecoder().decode(response.getFile().getBytes(StandardCharsets.UTF_8)));
  }

  void updateFile(DocumentBarcode documentBarcode, DocumentFile documentFile) {
    fileServiceApi.updateFile(
        new UpdatingFileRequestFsExt()
            .barcode(documentBarcode.value())
            ._file(Base64.getEncoder().encodeToString(documentFile.bytes())));
  }
}
