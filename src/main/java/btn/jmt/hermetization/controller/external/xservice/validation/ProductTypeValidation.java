package btn.jmt.hermetization.controller.external.xservice.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ProductTypeValidator.class)
public @interface ProductTypeValidation {
    String message() default "Invalid product type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
