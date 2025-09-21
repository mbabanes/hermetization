package btn.jmt.hermetization.service.process;

import btn.jmt.hermetization.controller.external.xservice.model.XServiceRequest;
import btn.jmt.hermetization.service.process.dto.*;
import java.time.LocalDateTime;
import java.util.List;

public interface ProcessService {

  ProcessDetails createProcess(XServiceRequest request);

  PhoneNumber findPhoneNumber(ProcessNumber processNumber);

  void updateDocumentSignatureDateTime(
      ProcessNumber processNumber, LocalDateTime signatureDateTime);

  void updateProcessState(ProcessNumber processNumber, ProcessState processState);

  EmployeeId getConsultantId(ProcessNumber processNumber);

  EmailAddress getClientEmail(ProcessNumber processNumber);

  List<ProcessNumber> findExpiredProcesses();
}
