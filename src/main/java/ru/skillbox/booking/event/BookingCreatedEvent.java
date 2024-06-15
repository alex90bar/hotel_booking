package ru.skillbox.booking.event;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingCreatedEvent {

    private Long userId;
    private LocalDate dtStart;
    private LocalDate dtEnd;

}
