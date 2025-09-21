package btn.jmt.hermetization.service.process;

import btn.jmt.hermetization.controller.external.xservice.model.XServiceRequest;
import btn.jmt.hermetization.service.process.dto.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class ProcessServiceImpl implements ProcessService {

  private final ProcessRepository processRepository;

  @Override
  public ProcessDetails createProcess(XServiceRequest request) {
    final var processNumber = new ProcessNumber(UUID.randomUUID().toString());
    log.info("Creating process {}", processNumber);
    return processRepository.save(ProcessEntity.createNew(processNumber)).toDto();
  }

  @Override
  public PhoneNumber findPhoneNumber(ProcessNumber processNumber) {
    return null;
  }

  @Override
  public void updateDocumentSignatureDateTime(
      ProcessNumber processNumber, LocalDateTime signatureDateTime) {}

  @Override
  public void updateProcessState(ProcessNumber processNumber, ProcessState processState) {}

  @Override
  public EmployeeId getConsultantId(ProcessNumber processNumber) {
    return null;
  }

  @Override
  public EmailAddress getClientEmail(ProcessNumber processNumber) {
    return null;
  }

  @Override
  public List<ProcessNumber> findExpiredProcesses() {
    return List.of();
  }
}
