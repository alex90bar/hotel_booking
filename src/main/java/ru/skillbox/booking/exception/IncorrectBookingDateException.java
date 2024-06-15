package ru.skillbox.booking.exception;

/**
 * IncorrectBookingDateException
 *
 * @author alex90bar
 */

public class IncorrectBookingDateException extends RuntimeException {

    public IncorrectBookingDateException() {
        super("Дата окончания бронирования должна быть позже даты начала бронирования!");
    }
}
