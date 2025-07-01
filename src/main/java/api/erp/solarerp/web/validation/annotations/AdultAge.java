package api.erp.solarerp.web.validation.annotations;

import api.erp.solarerp.web.validation.validators.AdultAgeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AdultAgeValidator.class)
public @interface AdultAge {
    String message() default "A idade deve ser entre 18 e 120 anos e não pode ser no futuro!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
