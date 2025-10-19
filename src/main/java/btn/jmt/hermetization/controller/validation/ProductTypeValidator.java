package btn.jmt.hermetization.controller.validation;

import btn.jmt.hermetization.controller.model.ProductType;
import btn.jmt.hermetization.controller.model.CreateProcessProduct;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.util.Objects.isNull;

@Component
class ProductTypeValidator implements ConstraintValidator<ProductTypeValidation, CreateProcessProduct> {

    @Override
    public boolean isValid(CreateProcessProduct product, ConstraintValidatorContext context) {
        if (isNull(product) || isNull(product.getProductType())) {
            return true;
        }
        if (ProductType.A.equals(product.getProductType()) && product.getPrice().compareTo(BigDecimal.TEN) > 0) {
            return false;
        }
        return true;
    }
}
