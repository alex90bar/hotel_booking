package ru.skillbox.hotelbooking.service.impl;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skillbox.hotelbooking.exception.BookingAlreadyExistException;
import ru.skillbox.hotelbooking.exception.HotelNotFoundException;
import ru.skillbox.hotelbooking.exception.RoomNotFoundException;
import ru.skillbox.hotelbooking.exception.UserNotFoundException;
import ru.skillbox.hotelbooking.model.Booking;
import ru.skillbox.hotelbooking.repository.BookingRepository;
import ru.skillbox.hotelbooking.repository.HotelRepository;
import ru.skillbox.hotelbooking.repository.RoomRepository;
import ru.skillbox.hotelbooking.repository.UserRepository;
import ru.skillbox.hotelbooking.service.DatabaseCheckService;

/**
 * DatabaseCheckServiceImpl
 *
 * @author alex90bar
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class DatabaseCheckServiceImpl implements DatabaseCheckService {

    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    @Override
    public void checkIfHotelExists(Long hotelId) {
        if (!hotelRepository.existsById(hotelId)) {
            throw new HotelNotFoundException();
        }
    }

    @Override
    public void checkIfUserExists(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException();
        }
    }

    @Override
    public void checkIfRoomExists(Long roomId) {
        if (!roomRepository.existsById(roomId)) {
            throw new RoomNotFoundException();
        }
    }

    @Override
    public void checkIfRoomDatesFree(Booking booking) {
        Long roomId = booking.getRoom().getId();
        LocalDate dtStart = booking.getDtStart();
        LocalDate dtEnd = booking.getDtEnd();
        List<Booking> bookingsByDates = bookingRepository.findBookingsByDates(dtStart, dtEnd, roomId);
        if (!bookingsByDates.isEmpty()) {
            log.error("Уже есть бронирования на эти даты ({} - {}) для комнаты с ИД {}: {}", dtStart, dtEnd, roomId, bookingsByDates);
            throw new BookingAlreadyExistException();
        }
    }
}


