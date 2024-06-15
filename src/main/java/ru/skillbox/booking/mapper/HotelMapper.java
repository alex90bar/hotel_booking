package ru.skillbox.booking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.skillbox.booking.dto.hotel.HotelCreateRequest;
import ru.skillbox.booking.dto.hotel.HotelDto;
import ru.skillbox.booking.dto.hotel.HotelUpdateRequest;
import ru.skillbox.booking.model.Hotel;

/**
 * HotelMapper
 *
 * @author alex90bar
 */

@Mapper
public interface HotelMapper {

    Hotel toEntity(HotelCreateRequest request);

    HotelDto toDto(Hotel hotel);

    void updateHotelFromRequest(HotelUpdateRequest request, @MappingTarget Hotel hotel);
}
