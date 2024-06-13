package ru.skillbox.statistics.service.impl;

import com.opencsv.CSVWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skillbox.statistics.model.Booking;
import ru.skillbox.statistics.model.User;
import ru.skillbox.statistics.repository.BookingRepository;
import ru.skillbox.statistics.repository.UserRepository;
import ru.skillbox.statistics.service.CsvService;

/**
 * CsvServiceImpl
 *
 * @author alex90bar
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class CsvServiceImpl implements CsvService {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    @Override
    public byte[] getCsvUserStats() throws IOException {
        List<User> users = userRepository.findAll();

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
        List<Booking> bookings = bookingRepository.findAll();

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


