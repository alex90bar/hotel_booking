package ru.skillbox.hotelbooking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skillbox.hotelbooking.dto.room.RoomCreateRequest;
import ru.skillbox.hotelbooking.dto.room.RoomDto;
import ru.skillbox.hotelbooking.dto.room.RoomUpdateRequest;
import ru.skillbox.hotelbooking.model.Room;

/**
 * RoomMapper
 *
 * @author alex90bar
 */

@Mapper
public interface RoomMapper {

    @Mapping(target = "hotel", expression = "java(Hotel.builder().id(request.getHotelId()).build())")
    Room toEntity(RoomCreateRequest request);

    @Mapping(target = "hotelId", source = "room.hotel.id")
    RoomDto toDto(Room room);

    @Mapping(target = "room.hotel", expression = "java(Hotel.builder().id(request.getHotelId()).build())")
    void updateRoomFromRequest(RoomUpdateRequest request, @MappingTarget Room room);
}
