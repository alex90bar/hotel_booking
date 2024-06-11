package ru.skillbox.statistics.service;

import ru.skillbox.commonresources.event.BookingCreatedEvent;
import ru.skillbox.commonresources.event.UserCreatedEvent;

/**
 * StatisticsService
 *
 * @author alex90bar
 */

public interface StatisticsService {

    void processUserCreatedEvent(UserCreatedEvent event);

    void processBookingCreatedEvent(BookingCreatedEvent event);

}
