package ru.skillbox.booking.service.impl;

import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skillbox.booking.model.mongo.Booking;
import ru.skillbox.booking.model.mongo.User;
import ru.skillbox.booking.repository.mongo.BookingStatsRepository;
import ru.skillbox.booking.repository.mongo.UserStatsRepository;
import ru.skillbox.booking.service.StatisticsService;
import ru.skillbox.booking.event.BookingCreatedEvent;
import ru.skillbox.booking.event.UserCreatedEvent;

/**
 * StatisticsServiceImpl
 *
 * @author alex90bar
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final UserStatsRepository userStatsRepository;
    private final BookingStatsRepository bookingStatsRepository;

    @Override
    public void processUserCreatedEvent(UserCreatedEvent event) {
        User user = User.builder()
        .userId(event.getId())
        .login(event.getLogin())
        .dtCreated(new Date())
        .build();
        User saved = userStatsRepository.save(user);
        log.info("User saved to mongo: {}", saved);
    }

    @Override
    public void processBookingCreatedEvent(BookingCreatedEvent event) {
        Booking booking = new Booking();
        booking.setUserId(event.getUserId());
        booking.setDtStartFromLocalDate(event.getDtStart());
        booking.setDtEndFromLocalDate(event.getDtEnd());
        Booking saved = bookingStatsRepository.save(booking);
        log.info("Booking saved to mongo: {}", saved);
    }
}


