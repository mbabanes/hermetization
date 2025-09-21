package btn.jmt.hermetization.controller.external.xservice;

import btn.jmt.hermetization.controller.external.xservice.model.XServiceRequest;
import btn.jmt.hermetization.controller.external.xservice.model.XServiceResponse;
import btn.jmt.hermetization.service.process.ProcessService;
import btn.jmt.hermetization.service.process.dto.ProcessDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class XServiceController {

  private final ProcessService processService;

  @PostMapping("process")
  XServiceResponse createProcess(@RequestBody @Valid XServiceRequest request) {
    final ProcessDetails process = processService.createProcess(request);
    return XServiceResponse.from(process);
  }
}
