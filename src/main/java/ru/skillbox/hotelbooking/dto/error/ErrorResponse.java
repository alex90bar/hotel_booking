package ru.skillbox.hotelbooking.dto.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ErrorResponse
 *
 * @author alex90bar
 */

@Data
@AllArgsConstructor
public class ErrorResponse {

    @Schema(description = "Тип ошибки", example = "Не найдено данных в БД")
    private String error;

    @Schema(description = "Описание ошибки", example = "Нет отеля по данному ИД")
    private Object description;

}


