package btn.jmt.hermetization.controller.internal.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Error {

  private ErrorCode code;
  private String msg;
}
