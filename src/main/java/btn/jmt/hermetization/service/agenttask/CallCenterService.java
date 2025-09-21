package btn.jmt.hermetization.service.agenttask;

import btn.jmt.hermetization.service.agenttask.dto.TaskContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CallCenterService {

  public void sendTask(TaskContent taskContent) {
    log.info("Sending Call Center task {}", taskContent);
  }
}
