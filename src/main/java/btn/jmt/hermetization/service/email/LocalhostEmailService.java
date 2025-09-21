package btn.jmt.hermetization.service.email;

import btn.jmt.hermetization.service.document.dto.DocumentFile;
import btn.jmt.hermetization.service.email.dto.EmailInput;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
final class LocalhostEmailService implements EmailService {
  @Override
  public void sendEmail(EmailInput emailInput) {
    log.info("This is localhost email implementation Sending email {}", emailInput);
  }

  @Override
  public void sendEmail(EmailInput emailInput, Set<DocumentFile> documents) {
    log.info("This is localhost email implementation Sending email with documents {} {}", emailInput, documents.stream().map(DocumentFile::fileName).toList());
  }
}
