package btn.jmt.hermetization.service.email;

import btn.jmt.hermetization.service.document.dto.DocumentFile;
import btn.jmt.hermetization.service.email.dto.EmailInput;
import java.util.Set;

public sealed interface EmailService permits LocalhostEmailService, ProductionEmailService {

  void sendEmail(EmailInput emailInput);

  void sendEmail(EmailInput emailInput, Set<DocumentFile> documents);
}
