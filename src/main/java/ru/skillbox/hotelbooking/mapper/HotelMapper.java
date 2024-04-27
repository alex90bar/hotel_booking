package ru.skillbox.hotelbooking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.skillbox.hotelbooking.dto.hotel.HotelCreateRequest;
import ru.skillbox.hotelbooking.dto.hotel.HotelDto;
import ru.skillbox.hotelbooking.dto.hotel.HotelUpdateRequest;
import ru.skillbox.hotelbooking.model.Hotel;

/**
 * HotelMapper
 *
 * @author alex90bar
 */

@Mapper(componentModel = "spring")
public interface HotelMapper {

    Hotel toEntity(HotelCreateRequest request);

    HotelDto toDto(Hotel hotel);

    void updateHotelFromRequest(HotelUpdateRequest request, @MappingTarget Hotel hotel);
}
