package ru.skillbox.hotelbooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * HotelNotFoundException
 *
 * @author alex90bar
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HotelNotFoundException extends RuntimeException {

    public HotelNotFoundException() {
        super("Нет отеля по данному ИД");
    }
}
