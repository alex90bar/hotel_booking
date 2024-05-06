package ru.skillbox.hotelbooking.dto.booking;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.Data;

/**
 * BookingDto
 *
 * @author alex90bar
 */

@Data
public class BookingDto {

    @Schema(description = "Идентификатор", example = "5")
    private Long id;

    @Schema(description = "Дата начала бронирования")
    private LocalDate dtStart;

    @Schema(description = "Дата окончания бронирования")
    private LocalDate dtEnd;

    @Schema(description = "Идентификатор бронируемой комнаты", example = "5")
    private Long roomId;

    @Schema(description = "Идентификатор пользователя", example = "5")
    private Long userId;
}
