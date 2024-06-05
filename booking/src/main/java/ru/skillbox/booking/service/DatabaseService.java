package ru.skillbox.booking.service;

import java.util.List;
import ru.skillbox.booking.model.Booking;
import ru.skillbox.booking.model.Hotel;
import ru.skillbox.booking.model.Room;
import ru.skillbox.booking.model.User;

/**
 * DatabaseCheckService
 *
 * @author alex90bar
 */

public interface DatabaseService {

    void checkIfHotelExists(Long hotelId);

    void checkIfUserExists(Long userId);

    void checkIfRoomExists(Long roomId);

    void checkIfRoomDatesFree(Booking booking);

    void deleteBookingsByUser(User user);

    void deleteBookingsByRoom(Room room);

    List<Room> findRoomsByHotel(Hotel hotel);

    void deleteRooms(List<Room> rooms);
}
