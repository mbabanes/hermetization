package btn.jmt.hermetization.service.process;

import btn.jmt.hermetization.service.agenttask.CallCenterService;
import btn.jmt.hermetization.service.agenttask.dto.TaskContent;
import btn.jmt.hermetization.service.email.EmailService;
import btn.jmt.hermetization.service.email.dto.EmailInput;
import btn.jmt.hermetization.service.process.dto.EmailAddress;
import btn.jmt.hermetization.service.process.dto.ProcessNumber;
import btn.jmt.hermetization.service.process.dto.ProcessState;
import java.util.function.BiConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
final class ProcessNotificationSender {

  private final ProcessRepository processRepository;
  private final EmailService emailService;
  private final CallCenterService callCenterService;

  void sendNotification(ProcessNumber processNumber) {
    final ProcessState processState =
        processRepository.getProcessStateByProcessNumber(processNumber.value());
    if (ProcessState.SUMMARY.equals(processState)) {
      this.applyNotification(
          processNumber,
          new TaskContent("Summary state of " + processNumber),
          this::sendEmailAndSendTaskToCallCenter);
    } else if (ProcessState.NEW.equals(processState)) {
      this.applyNotification(
          processNumber, new TaskContent("NEW of " + processNumber), this::sendTaskToCallCenter);
    }
  }

  private void sendEmailAndSendTaskToCallCenter(
      ProcessNumber processNumber, TaskContent taskContent) {
    emailService.sendEmail(
        new EmailInput(
            processNumber,
            new EmailAddress("example@com.pl"),
            "Subject",
            "powiadomienia@btn.pl",
            taskContent.content()));
    callCenterService.sendTask(taskContent);
  }

  private void sendTaskToCallCenter(ProcessNumber processNumber, TaskContent taskContent) {
    callCenterService.sendTask(taskContent);
  }

  private void applyNotification(
      ProcessNumber processNumber,
      TaskContent taskContent,
      BiConsumer<ProcessNumber, TaskContent> sendingOperation) {
    sendingOperation.accept(processNumber, taskContent);
    processRepository.updateStateByProcessNumber(processNumber.value(), ProcessState.NOTIFIED);
  }
}
