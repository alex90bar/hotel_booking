package ru.skillbox.hotelbooking.exception;

/**
 * BookingAlreadyExistException
 *
 * @author alex90bar
 */

public class BookingAlreadyExistException extends RuntimeException {

    public BookingAlreadyExistException() {
        super("Данные даты для бронирования уже заняты!");
    }
}
