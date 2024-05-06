package ru.skillbox.hotelbooking.service;

import ru.skillbox.hotelbooking.model.Booking;

/**
 * DatabaseCheckService
 *
 * @author alex90bar
 */

public interface DatabaseCheckService {

    void checkIfHotelExists(Long hotelId);

    void checkIfUserExists(Long userId);

    void checkIfRoomExists(Long roomId);

    void checkIfRoomDatesFree(Booking booking);
}
