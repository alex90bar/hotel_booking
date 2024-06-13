package ru.skillbox.statistics.controller;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.statistics.service.CsvService;

/**
 * StatisticsController
 *
 * @author alex90bar
 */

@Slf4j
@RestController
@RequiredArgsConstructor
public class StatisticsController {

    private final CsvService csvService;

    private static final String BOOKINGS_FILENAME = "bookings.csv";
    private static final String USERS_FILENAME = "users.csv";
    private static final String CONTENT_DISPOSITION = "attachment; filename=%s";
    private static final String CONTENT_TYPE = "text/csv";

    @GetMapping("/getUsersCsv")
    public ResponseEntity<byte[]> getUsersCsv() throws IOException {
        byte[] csvUserStats = csvService.getCsvUserStats();
        return generateResponse(csvUserStats, USERS_FILENAME);
    }

    @GetMapping("/getBookingsCsv")
    public ResponseEntity<byte[]> getBookingsCsv() throws IOException {
        byte[] csvBookingStats = csvService.getCsvBookingStats();
        return generateResponse(csvBookingStats, BOOKINGS_FILENAME);
    }

    @NotNull
    private ResponseEntity<byte[]> generateResponse(byte[] csvBookingStats, String filename) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, String.format(CONTENT_DISPOSITION, filename));
        headers.add(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE);
        return new ResponseEntity<>(csvBookingStats, headers, HttpStatus.OK);
    }
}
