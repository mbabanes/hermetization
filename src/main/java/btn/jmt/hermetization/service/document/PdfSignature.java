package btn.jmt.hermetization.service.document;

import btn.jmt.hermetization.service.document.dto.DocumentFile;
import btn.jmt.hermetization.service.process.dto.PhoneNumber;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class PdfSignature {

  DocumentFile putSignatureToDocument(
      DocumentFile documentFile, PhoneNumber phoneNumber, LocalDateTime signatureDateTime) {
    log.debug("Adding signature to document file");
    try {
      return putSignatureToAllPagesAtTheBottomRightCorner(
          documentFile, phoneNumber, signatureDateTime);
    } catch (IOException ex) {
      log.error(
          "Cant sign document {} {} exceptionMsg={}",
          phoneNumber,
          documentFile.fileName(),
          ex.getMessage(),
          ex);
      throw new DocumentSignatureException(ex);
    }
  }

  private DocumentFile putSignatureToAllPagesAtTheBottomRightCorner(
      DocumentFile documentFile, PhoneNumber phoneNumber, LocalDateTime signatureDateTime)
      throws IOException {

    return new DocumentFile(
        addFooterToPdf(documentFile.bytes(), phoneNumber.value(), signatureDateTime),
        documentFile.fileName());
  }

  private byte[] addFooterToPdf(
      byte[] documentBytes, String phoneNumber, LocalDateTime signatureDateTime)
      throws IOException {
    try (PDDocument document = Loader.loadPDF(documentBytes)) {
      for (PDPage page : document.getPages()) {
        try (var contentStream =
            new PDPageContentStream(
                document, page, PDPageContentStream.AppendMode.APPEND, true, true)) {
          contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
          contentStream.beginText();
          contentStream.setNonStrokingColor(Color.BLUE);
          contentStream.newLineAtOffset(10, 20);
          contentStream.showText("Podpis elektroniczny nr tel " + phoneNumber);
          contentStream.newLineAtOffset(0, -10);
          contentStream.showText(
              "Data podpisu " + signatureDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
          contentStream.endText();
        }
      }
      final var outputPdf = new ByteArrayOutputStream();
      document.save(outputPdf);
      return outputPdf.toByteArray();
    }
  }

  private static class DocumentSignatureException extends RuntimeException {
    DocumentSignatureException(Throwable cause) {
      super("Problem with adding signature to document has occurred", cause);
    }
  }
}
