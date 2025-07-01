package api.erp.solarerp.web.validation.annotations;

import api.erp.solarerp.web.validation.validators.BrazilianPhoneValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BrazilianPhoneValidator.class)
public @interface BrazilianPhone {
    String message() default "Formato de telefone brasileiro inválido.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}