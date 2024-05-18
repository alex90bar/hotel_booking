package ru.skillbox.hotelbooking.service;

import java.util.List;
import ru.skillbox.hotelbooking.model.Booking;
import ru.skillbox.hotelbooking.model.Hotel;
import ru.skillbox.hotelbooking.model.Room;
import ru.skillbox.hotelbooking.model.User;

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
