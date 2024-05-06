package ru.skillbox.hotelbooking.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * LocalDateValidator
 *
 * @author alex90bar
 */

public class LocalDateValidator implements ConstraintValidator<ValidLocalDate, String> {

    @Override
    public void initialize(ValidLocalDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        try {
            LocalDate.parse(value);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
