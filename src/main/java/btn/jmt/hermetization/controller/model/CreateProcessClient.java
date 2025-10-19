package btn.jmt.hermetization.controller.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateProcessClient {
  @NotEmpty
  @Size(max = 30)
  private String clientId;

  @NotEmpty
  @Size(max = 255)
  private String firstName;

  @NotEmpty
  @Size(max = 255)
  private String lastName;

  @NotEmpty
  @Email
  @Size(max = 255)
  private String email;
}
