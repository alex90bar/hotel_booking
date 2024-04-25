package ru.skillbox.hotelbooking.dto.room;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.skillbox.hotelbooking.model.Hotel;

/**
 * RoomDto
 *
 * @author alex90bar
 */

@Data
public class RoomDto {

    @Schema(description = "Идентификатор", example = "5")
    private Long id;

    @Schema(description = "Название комнаты", example = "Номер делюкс с балконом и видом на горы")
    private String roomName;

    @Schema(description = "Описание комнаты", example = "Комфортный номер с балконом с видом на горы. Номер оборудован индивидуальной системой вентиляции воздуха, феном, телевизором с плазменной панелью.")
    private String description;

    @Schema(description = "Номер комнаты", example = "107")
    private Integer roomNumber;

    @Schema(description = "Цена", example = "2400")
    private Integer price;

    @Schema(description = "Максимальное количество людей, которое можно разместить", example = "3")
    private Integer peopleCapacity;

    @Schema(description = "Даты, когда комната недоступна")
    private String unavailableDates;

    @Schema(description = "ИД отеля, в котором находится комната")
    private Long hotelId;
}
