package ru.skillbox.hotelbooking.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
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

    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "marksCount", ignore = true)
    Hotel toEntity(HotelUpdateRequest request);

    HotelDto toHotelDto(Hotel hotel);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateHotelFromRequest(HotelUpdateRequest request, @MappingTarget Hotel hotel);
}
