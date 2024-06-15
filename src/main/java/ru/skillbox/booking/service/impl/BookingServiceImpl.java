package ru.skillbox.booking.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.skillbox.booking.dto.booking.BookingCreateRequest;
import ru.skillbox.booking.dto.booking.BookingDto;
import ru.skillbox.booking.exception.IncorrectBookingDateException;
import ru.skillbox.booking.mapper.BookingMapper;
import ru.skillbox.booking.messaging.KafkaSender;
import ru.skillbox.booking.model.Booking;
import ru.skillbox.booking.repository.BookingRepository;
import ru.skillbox.booking.service.BookingService;
import ru.skillbox.booking.service.DatabaseService;

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
    private final DatabaseService databaseService;
    private final BookingMapper bookingMapper;
    private final KafkaSender kafkaSender;

    @Override
    public BookingDto create(BookingCreateRequest request) {
        databaseService.checkIfRoomExists(request.getRoomId());
        databaseService.checkIfUserExists(request.getUserId());
        Booking booking = bookingMapper.toEntity(request);
        checkIfDatesCorrect(booking);
        databaseService.checkIfRoomDatesFree(booking);
        Booking saved = bookingRepository.save(booking);
        kafkaSender.sendBookingCreatedEvent(saved);
        return bookingMapper.toDto(saved);
    }

    private void checkIfDatesCorrect(Booking booking) {
        if (!booking.getDtEnd().isAfter(booking.getDtStart())) {
            throw new IncorrectBookingDateException();
        }
    }

    @Override
    public Page<BookingDto> getAll(Pageable pageable) {
        return bookingRepository.findAll(pageable).map(bookingMapper::toDto);
    }
}