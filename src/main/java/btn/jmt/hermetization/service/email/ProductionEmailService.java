package btn.jmt.hermetization.service.email;

import btn.jmt.hermetization.service.document.dto.DocumentFile;
import btn.jmt.hermetization.service.email.dto.EmailInput;
import java.util.Set;

final class ProductionEmailService implements EmailService {
  @Override
  public void sendEmail(EmailInput emailInput) {}

  @Override
  public void sendEmail(EmailInput emailInput, Set<DocumentFile> documents) {}
}
