package ru.skillbox.booking.dto.hotel;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * HotelRateRequest
 *
 * @author alex90bar
 */

@Data
public class HotelRateRequest {

    @NotNull
    @Min(value = 1)
    @Schema(description = "Идентификатор отеля", example = "5")
    private Long id;

    @NotNull
    @Min(value = 1)
    @Max(value = 5)
    @Schema(description = "Рейтинг (от 1 до 5)", example = "5")
    private Integer rating;
}