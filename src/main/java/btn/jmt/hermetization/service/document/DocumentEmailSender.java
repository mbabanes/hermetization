package btn.jmt.hermetization.service.document;

import btn.jmt.hermetization.service.document.dto.DocumentFile;
import btn.jmt.hermetization.service.email.EmailService;
import btn.jmt.hermetization.service.email.dto.EmailInput;
import btn.jmt.hermetization.service.process.ProcessService;
import btn.jmt.hermetization.service.process.dto.EmailAddress;
import btn.jmt.hermetization.service.process.dto.ProcessNumber;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class DocumentEmailSender {

  private final EmailService emailService;
  private final DocumentProperties documentProperties;
  private final ProcessService processService;

  void sendEmailToClient(ProcessNumber processNumber, DocumentFile document) {
    log.info("Sending email with documents to client of {}", processNumber);
    final EmailAddress clientEmail = processService.getClientEmail(processNumber);

    emailService.sendEmail(
        new EmailInput(
            processNumber,
            clientEmail,
            documentProperties.getEmail().subject(),
            documentProperties.getEmail().senderAddress(),
            documentProperties.getEmail().textContent()),
        Set.of(document));
  }
}
