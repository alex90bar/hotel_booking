package ru.skillbox.hotelbooking.advice;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.skillbox.hotelbooking.dto.error.ErrorResponse;
import ru.skillbox.hotelbooking.dto.error.ErrorType;
import ru.skillbox.hotelbooking.exception.BookingAlreadyExistException;
import ru.skillbox.hotelbooking.exception.HotelNotFoundException;
import ru.skillbox.hotelbooking.exception.IncorrectBookingDateException;
import ru.skillbox.hotelbooking.exception.RoomNotFoundException;
import ru.skillbox.hotelbooking.exception.UserAlreadyExistException;
import ru.skillbox.hotelbooking.exception.UserNotFoundException;

/**
 * MainControllerAdvice
 *
 * @author alex90bar
 */

@Slf4j
@RestControllerAdvice
public class MainControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationExceptions(
        MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ErrorResponse(ErrorType.VALIDATION_ERROR.getDescription(), errors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({UserAlreadyExistException.class, BookingAlreadyExistException.class})
    public ErrorResponse handleUserAlreadyExistExceptions(
        Exception ex) {
        return new ErrorResponse(ErrorType.BAD_REQUEST.getDescription(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IncorrectBookingDateException.class})
    public ErrorResponse handleIncorrectDateExceptions(
        Exception ex) {
        return new ErrorResponse(ErrorType.INCORRECT_DATES.getDescription(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({AccessDeniedException.class})
    public ErrorResponse handleAccessDeniedExceptions(
        Exception ex) {
        return new ErrorResponse(ErrorType.ACCESS_DENIED.getDescription(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({HotelNotFoundException.class, RoomNotFoundException.class, UserNotFoundException.class})
    public ErrorResponse handleNotFoundExceptions(
        Exception ex) {
        return new ErrorResponse(ErrorType.NOT_FOUND.getDescription(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleInternalErrors(
        Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ErrorResponse(ErrorType.INTERNAL_SERVER_ERROR.getDescription(), ex.getMessage());
    }
}