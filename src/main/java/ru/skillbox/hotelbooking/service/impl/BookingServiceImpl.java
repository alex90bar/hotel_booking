package ru.skillbox.hotelbooking.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skillbox.hotelbooking.dto.booking.BookingCreateRequest;
import ru.skillbox.hotelbooking.dto.booking.BookingDto;
import ru.skillbox.hotelbooking.exception.IncorrectBookingDateException;
import ru.skillbox.hotelbooking.mapper.BookingMapper;
import ru.skillbox.hotelbooking.model.Booking;
import ru.skillbox.hotelbooking.repository.BookingRepository;
import ru.skillbox.hotelbooking.service.BookingService;
import ru.skillbox.hotelbooking.service.DatabaseCheckService;

/**
 * BookingServiceImpl
 *
 * @author alex90bar
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final DatabaseCheckService databaseCheckService;
    private final BookingMapper bookingMapper;

    @Override
    public BookingDto create(BookingCreateRequest request) {
        databaseCheckService.checkIfRoomExists(request.getRoomId());
        databaseCheckService.checkIfUserExists(request.getUserId());
        Booking booking = bookingMapper.toEntity(request);
        checkIfDatesCorrect(booking);
        databaseCheckService.checkIfRoomDatesFree(booking);
        Booking saved = bookingRepository.save(booking);
        return bookingMapper.toDto(saved);
    }

    private void checkIfDatesCorrect(Booking booking) {
        if (!booking.getDtEnd().isAfter(booking.getDtStart())) {
            throw new IncorrectBookingDateException();
        }
    }

    @Override
    public List<BookingDto> getAll() {
        return bookingRepository.findAll().stream().map(bookingMapper::toDto).toList();
    }
}