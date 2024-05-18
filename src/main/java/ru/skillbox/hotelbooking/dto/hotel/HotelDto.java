package ru.skillbox.hotelbooking.dto.hotel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * HotelDto
 *
 * @author alex90bar
 */

@Data
public class HotelDto {

    @Schema(description = "Идентификатор", example = "5")
    private Long id;

    @Schema(description = "Название отеля", example = "Cardiff Marriott Hotel")
    private String hotelName;

    @Schema(description = "Заголовок объявления", example = "Отель Cardiff Marriott Hotel")
    private String advertisementTitle;

    @Schema(description = "Город", example = "Кардифф")
    private String city;

    @Schema(description = "Город", example = "Mill Lane, Кардифф CF10 1EZ Уэльс")
    private String address;

    @Schema(description = "Расстояние от центра города, метры", example = "330")
    private Integer distanceToCityCenter;

    @Schema(description = "Рейтинг (от 1 до 5)", example = "5")
    private Double rating;

    @Schema(description = "Количество оценок, на основе которых был рассчитан рейтинг", example = "330")
    private Integer marksCount;
}
