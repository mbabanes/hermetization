package btn.jmt.hermetization.service.identityverification;

import btn.jmt.hermetization.service.employee.EmployeeService;
import btn.jmt.hermetization.service.employee.dto.EmployeeData;
import btn.jmt.hermetization.service.email.EmailService;
import btn.jmt.hermetization.service.email.dto.EmailInput;
import btn.jmt.hermetization.service.process.ProcessService;
import btn.jmt.hermetization.service.process.dto.EmployeeId;
import btn.jmt.hermetization.service.process.dto.ProcessNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class IdVerificationEmailSender {

  private final EmailService emailService;
  private final IdVerificationProperties idVerificationProperties;
  private final EmployeeService employeeService;
  private final ProcessService processService;

  void sendEmailThatClientHasBeenVerified(ProcessNumber processNumber) {
    log.info("Sending email to consultant of {}", processNumber);

    final EmployeeId employeeId = processService.getConsultantId(processNumber);
    final EmployeeData consultant = employeeService.findConsultant(employeeId);
    emailService.sendEmail(
        new EmailInput(
            processNumber,
            consultant.emailAddress(),
            String.format(idVerificationProperties.getEmail().subject(), processNumber.value()),
            idVerificationProperties.getEmail().senderAddress(),
            String.format(
                idVerificationProperties.getEmail().textContent(), processNumber.value())));
  }
}
