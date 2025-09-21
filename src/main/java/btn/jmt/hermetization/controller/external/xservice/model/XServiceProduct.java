package btn.jmt.hermetization.controller.external.xservice.model;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Setter(AccessLevel.PACKAGE)
public class XServiceProduct {

    @NotEmpty
    private String id;

    @NotNull
    @Digits(integer = 5, fraction = 2)
    private BigDecimal price;

    private ProductType productType;
}
