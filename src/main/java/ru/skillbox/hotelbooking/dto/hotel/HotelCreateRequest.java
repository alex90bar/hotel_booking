package ru.skillbox.hotelbooking.dto.hotel;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * HotelCreateRequest
 *
 * @author alex90bar
 */

@Data
public class HotelCreateRequest {

    @NotBlank
    @Schema(description = "Название отеля", example = "Cardiff Marriott Hotel")
    private String hotelName;

    @NotBlank
    @Schema(description = "Заголовок объявления", example = "Отель Cardiff Marriott Hotel")
    private String advertisementTitle;

    @NotBlank
    @Schema(description = "Город", example = "Кардифф")
    private String city;

    @NotBlank
    @Schema(description = "Адрес", example = "Mill Lane, Кардифф CF10 1EZ Уэльс")
    private String address;

    @NotNull
    @Schema(description = "Расстояние от центра города, метры", example = "330")
    private Integer distanceToCityCenter;
}
