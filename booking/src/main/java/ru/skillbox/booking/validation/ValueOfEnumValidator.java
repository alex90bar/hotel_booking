package ru.skillbox.booking.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * ValueOfEnumValidator
 *
 * @author alex90bar
 */

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, CharSequence> {

    private List<String> acceptedValues;
    private static final String ERROR_DESCRIPTION = "Значение должно быть из списка: %s";

    @Override
    public void initialize(ValueOfEnum annotation) {
        acceptedValues = Arrays.stream(annotation.enumClass().getEnumConstants())
            .map(Enum::name)
            .toList();
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        boolean isValid = acceptedValues.contains(value.toString());
        if (!isValid) {
            String joinedValues = String.join(", ", acceptedValues);
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format(ERROR_DESCRIPTION, joinedValues))
                .addConstraintViolation();
        }
        return isValid;
    }
}
