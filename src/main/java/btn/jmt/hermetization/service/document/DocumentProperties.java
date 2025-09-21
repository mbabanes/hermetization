package btn.jmt.hermetization.service.document;

import btn.jmt.hermetization.configuration.EmailProperty;
import java.util.Set;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("document")
class DocumentProperties {

  private static final String DEFAULT_TEXT_CONTENT = "Przesyłamy dokumenty";
  private static final String DEFAULT_SUBJECT = "Dokumenty";

  private EmailProperty email = EmailProperty.withDefaults(DEFAULT_SUBJECT, DEFAULT_TEXT_CONTENT);

  private UploadingFileProperties uploadingFile = UploadingFileProperties.withDefaults();

  record UploadingFileProperties(
      Set<String> allowedExtensions, long maximumBytes, int maxFileNumber) {
    static UploadingFileProperties withDefaults() {
      return new UploadingFileProperties(Set.of(".pdf", ".jpg"), 50000, 3);
    }
  }
}
