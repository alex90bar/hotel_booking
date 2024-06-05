package ru.skillbox.booking.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import ru.skillbox.booking.model.Booking;
import ru.skillbox.booking.model.User;
import ru.skillbox.commonresources.event.BookingCreatedEvent;
import ru.skillbox.commonresources.event.UserCreatedEvent;

/**
 * KafkaSender
 *
 * @author alex90bar
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaSender {

    private final StreamBridge streamBridge;

    public void sendUserCreatedEvent(User user) {
        UserCreatedEvent userCreatedEvent = UserCreatedEvent.builder()
            .id(user.getId())
            .login(user.getLogin())
            .build();

        log.info("Отправка статистики по новому юзеру: {}", userCreatedEvent);
        streamBridge.send("output-user-stats", userCreatedEvent);
    }

    public void sendBookingCreatedEvent(Booking booking) {
        BookingCreatedEvent bookingCreatedEvent = BookingCreatedEvent.builder()
            .userId(booking.getUser().getId())
            .dtStart(booking.getDtStart())
            .dtEnd(booking.getDtEnd())
            .build();

        log.info("Отправка статистики по новому бронированию: {}", bookingCreatedEvent);
        streamBridge.send("output-booking-stats", bookingCreatedEvent);
    }

}
