package api.erp.solarerp.web.validation.validators;

import api.erp.solarerp.web.validation.annotations.BrazilianPhone;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BrazilianPhoneValidator implements ConstraintValidator<BrazilianPhone, String> {

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        // Campos não obrigatórios (como telefone secundário) podem ser nulos ou vazios
        if (phone == null || phone.isBlank()) {
            return true;
        }

        // Remove todos os caracteres não numéricos
        String digitsOnly = phone.replaceAll("[^\\d]", "");

        // Valida se o número possui 10 (fixo) ou 11 (móvel) dígitos
        // Ex: 9832234567 (10) ou 98982552337 (11)
        return digitsOnly.length() == 10 || digitsOnly.length() == 11;
    }
}