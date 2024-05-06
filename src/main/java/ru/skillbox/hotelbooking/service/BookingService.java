package ru.skillbox.hotelbooking.service;

import java.util.List;
import ru.skillbox.hotelbooking.dto.booking.BookingCreateRequest;
import ru.skillbox.hotelbooking.dto.booking.BookingDto;

/**
 * BookingService
 *
 * @author alex90bar
 */

public interface BookingService {

    BookingDto create(BookingCreateRequest request);

    List<BookingDto> getAll();
}
