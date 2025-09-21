package btn.jmt.hermetization.service.process.expired;

import btn.jmt.hermetization.service.process.ProcessService;
import btn.jmt.hermetization.service.process.dto.ProcessState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

@Slf4j
@RequiredArgsConstructor
class ExpiredProcessJob implements Job {

  private final ProcessService processService;

  @Override
  public void execute(JobExecutionContext context) {
    log.info("ExpiredProcessJob");
    processService
        .findExpiredProcesses()
        .forEach(
            processNumber ->
                processService.updateProcessState(processNumber, ProcessState.EXPIRED));
  }
}
