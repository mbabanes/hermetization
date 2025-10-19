package btn.jmt.hermetization.controller.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ErrorResponse {

  private List<Error> errors = new ArrayList<>();

  public static ErrorResponse with(List<Error> errors) {
    final ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setErrors(errors);
    return errorResponse;
  }
}
