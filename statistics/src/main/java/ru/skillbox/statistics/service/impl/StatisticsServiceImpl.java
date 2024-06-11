package ru.skillbox.statistics.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skillbox.commonresources.event.BookingCreatedEvent;
import ru.skillbox.commonresources.event.UserCreatedEvent;
import ru.skillbox.statistics.model.Booking;
import ru.skillbox.statistics.model.User;
import ru.skillbox.statistics.repository.BookingRepository;
import ru.skillbox.statistics.repository.UserRepository;
import ru.skillbox.statistics.service.StatisticsService;

/**
 * StatisticsServiceImpl
 *
 * @author alex90bar
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    @Override
    public void processUserCreatedEvent(UserCreatedEvent event) {
        User user = User.builder()
        .userId(event.getId())
        .login(event.getLogin())
        .build();
        User saved = userRepository.save(user);
        log.info("User saved to mongo: {}", saved);
    }

    @Override
    public void processBookingCreatedEvent(BookingCreatedEvent event) {
        Booking booking = new Booking();
        booking.setUserId(event.getUserId());
        booking.setDtStartFromLocalDate(event.getDtStart());
        booking.setDtEndFromLocalDate(event.getDtEnd());
        Booking saved = bookingRepository.save(booking);
        log.info("Booking saved to mongo: {}", saved);
    }
}


