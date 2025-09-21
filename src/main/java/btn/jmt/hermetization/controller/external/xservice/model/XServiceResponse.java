package btn.jmt.hermetization.controller.external.xservice.model;

import btn.jmt.hermetization.service.process.dto.ProcessDetails;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.PACKAGE)
public class XServiceResponse {

  private String processNumber;
  private String state;

  public static XServiceResponse from(ProcessDetails process) {
    final var response = new XServiceResponse();
    response.setState(process.processState().name());
    response.setProcessNumber(process.processNumber().value());
    return response;
  }
}
