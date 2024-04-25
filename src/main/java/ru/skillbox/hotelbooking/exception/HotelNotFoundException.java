package ru.skillbox.hotelbooking.exception;

/**
 * HotelNotFoundException
 *
 * @author alex90bar
 */

public class HotelNotFoundException extends RuntimeException {

    public HotelNotFoundException() {
        super("Нет отеля по данному ИД");
    }
}
