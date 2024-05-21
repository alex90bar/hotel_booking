package ru.skillbox.hotelbooking.dto.booking;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.skillbox.hotelbooking.validation.ValidLocalDate;

/**
 * BookingDto
 *
 * @author alex90bar
 */

@Data
public class BookingCreateRequest {

    @NotNull
    @ValidLocalDate
    @Schema(description = "Дата начала бронирования", example = "2024-05-31")
    private String dtStart;

    @NotNull
    @ValidLocalDate
    @Schema(description = "Дата окончания бронирования", example = "2024-06-05")
    private String dtEnd;

    @NotNull
    @Min(value = 1)
    @Schema(description = "Идентификатор бронируемой комнаты", example = "5")
    private Long roomId;

    @NotNull
    @Min(value = 1)
    @Schema(description = "Идентификатор пользователя", example = "5")
    private Long userId;
}
