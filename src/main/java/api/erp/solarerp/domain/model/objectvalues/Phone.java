package api.erp.solarerp.domain.model.objectvalues;

import api.erp.solarerp.infrastructure.exceptions.IllegalPhoneFormatException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.NumberParseException;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode
@Embeddable
@NoArgsConstructor
@Getter
@ToString
public class Phone {
    private static final PhoneNumberUtil PHONE_UTIL = PhoneNumberUtil.getInstance();
    private static final String DEFAULT_REGION = "BR";

    private String number; // Armazenado no formato internacional +5511999999999

    public Phone(String value) {
        this.number = validateAndFormat(value.trim());
    }

    private String validateAndFormat(String input) {
        try {
            Phonenumber.PhoneNumber parsed = PHONE_UTIL.parse(input, DEFAULT_REGION);

            if (!PHONE_UTIL.isValidNumber(parsed))
                throw new IllegalPhoneFormatException("Phone number is not valid in Brazil: " + input);

            return PHONE_UTIL.format(parsed, PhoneNumberUtil.PhoneNumberFormat.E164);
        }
        catch (NumberParseException e) {
            throw new IllegalPhoneFormatException("Phone number could not be parsed: " + e);
        }
    }
}