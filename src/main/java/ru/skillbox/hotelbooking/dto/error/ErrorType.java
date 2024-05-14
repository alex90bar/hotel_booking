package ru.skillbox.hotelbooking.dto.error;

import lombok.Getter;

@Getter
public enum ErrorType {

    VALIDATION_ERROR("Неправильный ввод, проверьте поля"),
    NOT_FOUND("Не найдено данных в БД"),
    ACCESS_DENIED("Недостаточно прав для данного действия"),
    BAD_REQUEST("Данные уже есть в системе"),
    INCORRECT_DATES("Ошибка ввода даты"),
    INTERNAL_SERVER_ERROR("Внутренняя ошибка сервера");

    private final String description;

    ErrorType(String description) {
        this.description = description;
    }
}
