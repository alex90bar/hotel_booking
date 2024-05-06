package ru.skillbox.hotelbooking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skillbox.hotelbooking.dto.booking.BookingCreateRequest;
import ru.skillbox.hotelbooking.dto.booking.BookingDto;
import ru.skillbox.hotelbooking.model.Booking;

/**
 * BookingMapper
 *
 * @author alex90bar
 */

@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(target = "room", expression = "java(Room.builder().id(request.getRoomId()).build())")
    @Mapping(target = "user", expression = "java(User.builder().id(request.getUserId()).build())")
    Booking toEntity(BookingCreateRequest request);

    @Mapping(target = "roomId", source = "booking.room.id")
    @Mapping(target = "userId", source = "booking.user.id")
    BookingDto toDto(Booking booking);
}
