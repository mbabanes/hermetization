package btn.jmt.hermetization.service.identityverification;

import static org.mockito.Mockito.*;

import btn.jmt.hermetization.service.employee.EmployeeService;
import btn.jmt.hermetization.service.employee.dto.EmployeeData;
import btn.jmt.hermetization.service.email.EmailService;
import btn.jmt.hermetization.service.email.dto.EmailInput;
import btn.jmt.hermetization.service.process.ProcessService;
import btn.jmt.hermetization.service.process.dto.EmployeeId;
import btn.jmt.hermetization.service.process.dto.EmailAddress;
import btn.jmt.hermetization.service.process.dto.ProcessNumber;
import org.junit.jupiter.api.Test;

class IdVerificationEmailSenderTest {

  private final EmailService emailService = mock(EmailService.class);
  private final IdVerificationProperties idVerificationProperties = new IdVerificationProperties();
  private final EmployeeService employeeService = mock(EmployeeService.class);
  private final ProcessService processService = mock(ProcessService.class);

  private final IdVerificationEmailSender idVerificationEmailSender =
      new IdVerificationEmailSender(
          emailService, idVerificationProperties, employeeService, processService);

  @Test
  void shouldSendEmailToConsultant() {
    // given
    final var processNumber = new ProcessNumber("pn");
    final var consultantId = new EmployeeId("cId");
    when(processService.getConsultantId(processNumber)).thenReturn(consultantId);
    final var consultantData =
        new EmployeeData(consultantId, new EmailAddress("email@example.pl"), "FName", "LName");
    when(employeeService.findConsultant(consultantId)).thenReturn(consultantData);

    // when
    idVerificationEmailSender.sendEmailThatClientHasBeenVerified(processNumber);

    // then
    verify(emailService)
        .sendEmail(
            new EmailInput(
                processNumber,
                consultantData.emailAddress(),
                String.format(idVerificationProperties.getEmail().subject(), processNumber.value()),
                idVerificationProperties.getEmail().senderName(),
                String.format(
                    idVerificationProperties.getEmail().textContent(), processNumber.value())));
  }
}
