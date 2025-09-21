package btn.jmt.hermetization.controller.external.xservice.model;

import btn.jmt.hermetization.controller.external.xservice.validation.ProductTypeValidation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@Setter(AccessLevel.PACKAGE)
public class XServiceRequest {

  @NotEmpty
  private String originProcessNumber;

  @NotNull
  @ProductTypeValidation
  private @Valid XServiceProduct product;

  @NotNull
  private @Valid XServiceClient client;
}
