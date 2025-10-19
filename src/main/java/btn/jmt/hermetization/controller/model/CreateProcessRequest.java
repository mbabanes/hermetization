package btn.jmt.hermetization.controller.model;

import btn.jmt.hermetization.controller.validation.ProductTypeValidation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@Setter(AccessLevel.PACKAGE)
public class CreateProcessRequest {

  @NotEmpty
  private String originProcessNumber;

  @NotNull
  @ProductTypeValidation
  private @Valid CreateProcessProduct product;

  @NotNull
  private @Valid CreateProcessClient client;
}
