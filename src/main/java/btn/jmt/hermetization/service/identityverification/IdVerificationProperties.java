package btn.jmt.hermetization.service.identityverification;

import btn.jmt.hermetization.configuration.EmailProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("id-verification")
class IdVerificationProperties {

  private static final String DEFAULT_EMAIL_SUBJECT =
      "Zweryfikowano tożsamość klienta nr wniosku %s";
  private static final String DEFAULT_EMAIL_TEXT_CONTENT =
      "Tożsamość klienta dla sprawy %s została zweryfikowana poprawnie";

  private EmailProperty email =
      EmailProperty.withDefaults(DEFAULT_EMAIL_SUBJECT, DEFAULT_EMAIL_TEXT_CONTENT);
}
