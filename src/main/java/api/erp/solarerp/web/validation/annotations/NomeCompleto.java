package api.erp.solarerp.web.validation.annotations;

import api.erp.solarerp.web.validation.validators.NomeCompletoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NomeCompletoValidator.class) // Conecta a anotação à sua lógica de validação
@Target({ ElementType.FIELD, ElementType.METHOD }) // Onde a anotação pode ser usada
@Retention(RetentionPolicy.RUNTIME) // Garante que a anotação estará disponível em tempo de execução
public @interface NomeCompleto {
    // Mensagem padrão caso nenhuma outra seja especificada na lógica
    String message() default "Nome completo inválido";

    // Padrão do Bean Validation, não precisa mexer
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}