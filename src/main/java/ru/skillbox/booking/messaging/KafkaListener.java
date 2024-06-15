package ru.skillbox.booking.messaging;

import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.skillbox.booking.service.StatisticsService;
import ru.skillbox.booking.event.BookingCreatedEvent;
import ru.skillbox.booking.event.UserCreatedEvent;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaListener {

    private final StatisticsService statisticsService;

    @Bean
    public Consumer<UserCreatedEvent> userCreatedConsumer() {
        return statisticsService::processUserCreatedEvent;
    }

    @Bean
    public Consumer<BookingCreatedEvent> bookingCreatedConsumer() {
        return statisticsService::processBookingCreatedEvent;
    }
}