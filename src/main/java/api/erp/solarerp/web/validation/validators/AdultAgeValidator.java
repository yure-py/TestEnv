package api.erp.solarerp.web.validation.validators;

import api.erp.solarerp.web.validation.annotations.AdultAge;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class AdultAgeValidator implements ConstraintValidator<AdultAge, LocalDate> {
    @Override
    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext context) {
        if (birthDate == null) {
            return false;
        }

        LocalDate today = LocalDate.now();

        // Verifica se a data de nascimento não é no futuro
        if (birthDate.isAfter(today)) {
            return false;
        }

        // Calcula a idade da pessoa.
        int age = Period.between(birthDate, today).getYears();

        // Verifica se a pessoa tem pelo menos 18 anos
        return age >= 18 && age <= 120;
    }
}
