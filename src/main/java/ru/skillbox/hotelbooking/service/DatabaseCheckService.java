package ru.skillbox.hotelbooking.service;

/**
 * DatabaseCheckService
 *
 * @author alex90bar
 */

public interface DatabaseCheckService {

    void checkIfHotelExists(Long hotelId);

}
