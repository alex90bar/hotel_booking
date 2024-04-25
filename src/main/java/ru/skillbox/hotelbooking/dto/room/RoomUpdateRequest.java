package ru.skillbox.hotelbooking.dto.room;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * RoomUpdateRequest
 *
 * @author alex90bar
 */

@Data
public class RoomUpdateRequest {

    @NotNull
    @Min(value = 1)
    @Schema(description = "Идентификатор комнаты", example = "5")
    private Long id;

    @NotBlank
    @Schema(description = "Название комнаты", example = "Номер делюкс с балконом и видом на горы")
    private String roomName;

    @NotBlank
    @Schema(description = "Описание комнаты", example = "Комфортный номер с балконом с видом на горы. Номер оборудован индивидуальной системой вентиляции воздуха, феном, телевизором с плазменной панелью.")
    private String description;

    @NotNull
    @Schema(description = "Номер комнаты", example = "107")
    private Integer roomNumber;

    @NotNull
    @Min(value = 1)
    @Schema(description = "Цена", example = "2400")
    private Integer price;

    @NotNull
    @Min(value = 1)
    @Schema(description = "Максимальное количество людей, которое можно разместить", example = "3")
    private Integer peopleCapacity;

    @Schema(description = "Даты, когда комната недоступна")
    private String unavailableDates;

    @NotNull
    @Min(value = 1)
    @Schema(description = "ИД отеля, в котором находится комната")
    private Long hotelId;
}
