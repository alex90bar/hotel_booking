package ru.skillbox.booking.service.impl;

import com.opencsv.CSVWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skillbox.booking.model.mongo.Booking;
import ru.skillbox.booking.model.mongo.User;
import ru.skillbox.booking.repository.mongo.BookingStatsRepository;
import ru.skillbox.booking.repository.mongo.UserStatsRepository;
import ru.skillbox.booking.service.CsvService;

/**
 * CsvServiceImpl
 *
 * @author alex90bar
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class CsvServiceImpl implements CsvService {

    private final UserStatsRepository userStatsRepository;
    private final BookingStatsRepository bookingStatsRepository;

    @Override
    public byte[] getCsvUserStats() throws IOException {
        List<User> users = userStatsRepository.findAll();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        OutputStreamWriter streamWriter = new OutputStreamWriter(out, StandardCharsets.UTF_8);
        CSVWriter writer = new CSVWriter(streamWriter);

        String[] header = {"id", "userId", "login", "registration date"};
        writer.writeNext(header);

        users.stream()
            .map(user -> new String[]{user.getId(), user.getUserId().toString(), user.getLogin(), user.getDtCreated().toString()})
            .forEach(writer::writeNext);
        writer.close();

        return out.toByteArray();
    }

    @Override
    public byte[] getCsvBookingStats() throws IOException {
        List<Booking> bookings = bookingStatsRepository.findAll();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        OutputStreamWriter streamWriter = new OutputStreamWriter(out, StandardCharsets.UTF_8);
        CSVWriter writer = new CSVWriter(streamWriter);

        String[] header = {"id", "userId", "dtStart", "dtEnd"};
        writer.writeNext(header);

        bookings.stream()
            .map(booking -> new String[]{booking.getId(), booking.getUserId().toString(), booking.getDtStart(), booking.getDtEnd()})
            .forEach(writer::writeNext);

        writer.close();

        return out.toByteArray();
    }
}


