package ru.skillbox.booking.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.skillbox.booking.dto.booking.BookingCreateRequest;
import ru.skillbox.booking.dto.booking.BookingDto;

/**
 * BookingService
 *
 * @author alex90bar
 */

public interface BookingService {

    BookingDto create(BookingCreateRequest request);

    Page<BookingDto> getAll(Pageable pageable);
}
