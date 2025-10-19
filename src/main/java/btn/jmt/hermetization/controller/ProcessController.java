package btn.jmt.hermetization.controller;

import btn.jmt.hermetization.controller.model.CreateProcessRequest;
import btn.jmt.hermetization.controller.model.CreateProcessResponse;
import btn.jmt.hermetization.service.process.ProcessService;
import btn.jmt.hermetization.service.process.dto.ProcessDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class ProcessController {

  private final ProcessService processService;

  @PostMapping("process")
  CreateProcessResponse createProcess(@RequestBody @Valid CreateProcessRequest request) {
    final ProcessDetails process = processService.createProcess(request);
    return CreateProcessResponse.from(process);
  }
}
