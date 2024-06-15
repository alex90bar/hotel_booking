package ru.skillbox.booking.service;

import ru.skillbox.booking.event.BookingCreatedEvent;
import ru.skillbox.booking.event.UserCreatedEvent;

/**
 * StatisticsService
 *
 * @author alex90bar
 */

public interface StatisticsService {

    void processUserCreatedEvent(UserCreatedEvent event);

    void processBookingCreatedEvent(BookingCreatedEvent event);

}
