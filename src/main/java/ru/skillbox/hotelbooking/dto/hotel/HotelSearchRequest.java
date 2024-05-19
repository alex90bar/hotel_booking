package ru.skillbox.hotelbooking.dto.hotel;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

/**
 * HotelSearchRequest
 *
 * @author alex90bar
 */

@Data
public class HotelSearchRequest {

    @Min(value = 1)
    @Schema(description = "Идентификатор отеля", example = "5")
    private Long id;

    @Schema(description = "Поиск по названию отеля, частичное совпадение, регистронезависимый", example = "Cardiff Marriott Hotel")
    private String hotelName;

    @Schema(description = "Поиск по заголовку объявления, частичное совпадение, регистронезависимый", example = "Отель Cardiff Marriott Hotel")
    private String advertisementTitle;

    @Schema(description = "Поиск по городу, частичное совпадение, регистронезависимый", example = "Кардифф")
    private String city;

    @Schema(description = "Поиск по адресу, частичное совпадение, регистронезависимый", example = "Mill Lane, Кардифф CF10 1EZ Уэльс")
    private String address;

    @Min(value = 1)
    @Schema(description = "Расстояние от центра города, метры", example = "330")
    private Integer distanceToCityCenter;

    @Min(value = 1)
    @Max(value = 5)
    @Schema(description = "Рейтинг (от 1 до 5)", example = "5")
    private Double rating;

    @Min(value = 1)
    @Schema(description = "Количество оценок, на основе которых был рассчитан рейтинг", example = "330")
    private Integer marksCount;
}
