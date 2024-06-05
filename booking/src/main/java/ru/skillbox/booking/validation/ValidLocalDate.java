package ru.skillbox.booking.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LocalDateValidator.class)
public @interface ValidLocalDate {
    String message() default "Неверный формат даты, пример: '2024-05-31'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
