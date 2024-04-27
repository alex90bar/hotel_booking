package ru.skillbox.hotelbooking.exception;

/**
 * UserAlreadyExistException
 *
 * @author alex90bar
 */

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException() {
        super("Данное имя пользователя или почта уже заняты!");
    }
}
