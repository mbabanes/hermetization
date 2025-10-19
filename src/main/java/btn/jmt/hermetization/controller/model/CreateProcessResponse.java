package btn.jmt.hermetization.controller.model;

import btn.jmt.hermetization.service.process.dto.ProcessDetails;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.PACKAGE)
public class CreateProcessResponse {

  private String processNumber;
  private String state;

  public static CreateProcessResponse from(ProcessDetails process) {
    final var response = new CreateProcessResponse();
    response.setState(process.processState().name());
    response.setProcessNumber(process.processNumber().value());
    return response;
  }
}
