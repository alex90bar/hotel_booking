package ru.skillbox.statistics.listener;

import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.skillbox.commonresources.event.BookingCreatedEvent;
import ru.skillbox.commonresources.event.UserCreatedEvent;
import ru.skillbox.statistics.service.StatisticsService;

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