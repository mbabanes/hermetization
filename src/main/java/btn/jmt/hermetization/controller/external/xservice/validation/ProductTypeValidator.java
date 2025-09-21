package btn.jmt.hermetization.controller.external.xservice.validation;

import btn.jmt.hermetization.controller.external.xservice.model.ProductType;
import btn.jmt.hermetization.controller.external.xservice.model.XServiceProduct;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.util.Objects.isNull;

@Component
class ProductTypeValidator implements ConstraintValidator<ProductTypeValidation, XServiceProduct> {

    @Override
    public boolean isValid(XServiceProduct product, ConstraintValidatorContext context) {
        if (isNull(product) || isNull(product.getProductType())) {
            return true;
        }
        if (ProductType.A.equals(product.getProductType()) && product.getPrice().compareTo(BigDecimal.TEN) > 0) {
            return false;
        }
        return true;
    }
}
