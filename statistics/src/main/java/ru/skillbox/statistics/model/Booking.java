package ru.skillbox.statistics.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Booking
 *
 * @author alex90bar
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "bookings")
public class Booking {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Id
    private String id;
    private Long userId;
    private String dtStart;
    private String dtEnd;

    public void setDtStartFromLocalDate(LocalDate dtStart) {
        this.dtStart = dtStart.format(DATE_FORMATTER);
    }

    public void setDtEndFromLocalDate(LocalDate dtEnd) {
        this.dtEnd = dtEnd.format(DATE_FORMATTER);
    }
}
