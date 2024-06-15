package ru.skillbox.booking.exception;

/**
 * RoomNotFoundException
 *
 * @author alex90bar
 */

public class RoomNotFoundException extends RuntimeException {

    public RoomNotFoundException() {
        super("Нет комнаты по данному ИД");
    }
}
