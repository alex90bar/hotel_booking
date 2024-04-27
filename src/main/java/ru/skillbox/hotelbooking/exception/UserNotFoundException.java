package ru.skillbox.hotelbooking.exception;

/**
 * UserNotFoundException
 *
 * @author alex90bar
 */

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("Нет пользователя по данному ИД");
    }
}
