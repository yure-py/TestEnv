package api.erp.solarerp.web.validation.validators;

import api.erp.solarerp.web.validation.annotations.NomeCompleto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NomeCompletoValidator implements ConstraintValidator<NomeCompleto, String> {

    @Override
    public boolean isValid(String nomeCompleto, ConstraintValidatorContext context) {
        if (nomeCompleto == null || nomeCompleto.isBlank()) {
            return true;
        }

        boolean isValid = true;

        // Desabilita a mensagem de erro padrão para podermos adicionar as nossas.
        context.disableDefaultConstraintViolation();

        // Regra 1: Não pode ter espaços no início ou no fim
        if (!nomeCompleto.trim().equals(nomeCompleto)) {
            context.buildConstraintViolationWithTemplate("O nome não pode conter espaços no início ou no fim.")
                    .addConstraintViolation();
            isValid = false;
        }

        // Regra 2: Não pode haver espaços consecutivos
        if (nomeCompleto.contains("  ")) {
            context.buildConstraintViolationWithTemplate("O nome não pode conter espaços consecutivos.")
                    .addConstraintViolation();
            isValid = false;
        }

        // Regra 3: Deve conter apenas letras e espaços
        if (!nomeCompleto.matches("^[a-zA-ZÀ-ÖØ-öø-ÿ\\s]+$")) {
            context.buildConstraintViolationWithTemplate("O nome deve conter apenas letras e espaços.")
                    .addConstraintViolation();
            isValid = false;
        }

        return isValid;
    }
}