package ru.skillbox.booking.dto.room;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Data;
import ru.skillbox.booking.validation.ValidLocalDate;

/**
 * RoomSearchRequest
 *
 * @author alex90bar
 */

@Data
public class RoomSearchRequest {

    @Min(value = 1)
    @Schema(description = "Идентификатор комнаты", example = "5")
    private Long id;

    @Schema(description = "Поиск по названию комнаты, частичное совпадение, регистронезависимый", example = "Номер делюкс с балконом и видом на горы")
    private String roomName;

    @Min(value = 1)
    @Schema(description = "Минимальная цена", example = "1200")
    private Integer minPrice;

    @Min(value = 1)
    @Schema(description = "Максимальная цена", example = "30000")
    private Integer maxPrice;

    @Min(value = 1)
    @Schema(description = "Количество гостей в комнате", example = "2")
    private Integer peopleCapacity;

    @ValidLocalDate
    @Schema(description = "Дата заезда", example = "2024-05-31")
    private String dtStart;

    @ValidLocalDate
    @Schema(description = "Дата выезда", example = "2024-06-05")
    private String dtEnd;

    @Min(value = 1)
    @Schema(description = "Идентификатор отеля", example = "5")
    private Long hotelId;

}
