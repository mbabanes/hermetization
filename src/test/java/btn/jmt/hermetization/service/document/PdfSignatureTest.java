package btn.jmt.hermetization.service.document;

import btn.jmt.hermetization.service.document.dto.DocumentFile;
import btn.jmt.hermetization.service.process.dto.PhoneNumber;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

class PdfSignatureTest {

  @Test
  @Disabled
  void runWithPdfFile() throws IOException {
    // given
    final byte[] documentBytes =
        Files.readAllBytes(
            Path.of(PdfSignatureTest.class.getResource("/sample.pdf").getFile().substring(1)));

    final DocumentFile signedDocument =
        new PdfSignature()
            .putSignatureToDocument(
                new DocumentFile(documentBytes, "sample.pdf"),
                new PhoneNumber("+48111222333"),
                LocalDateTime.now());

    final Path path = Path.of("signed-sample.pdf");
    Files.deleteIfExists(path);
    final Path signedFile = Files.createFile(path);
    Files.write(signedFile, signedDocument.bytes());
  }
}
