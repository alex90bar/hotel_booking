package ru.skillbox.booking.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.booking.service.CsvService;

/**
 * StatisticsController
 *
 * @author alex90bar
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
@SecurityRequirement(name = "basicAuth")
@Tag(name = "StatisticsController", description = "Получение статистических данных")
public class StatisticsController {

    private final CsvService csvService;

    private static final String BOOKINGS_FILENAME = "bookings.csv";
    private static final String USERS_FILENAME = "users.csv";
    private static final String CONTENT_DISPOSITION = "attachment; filename=%s";
    private static final String CONTENT_TYPE = "text/csv";

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Получение статистики по зарегистрированным пользователям")
    public ResponseEntity<byte[]> getUsersCsv() throws IOException {
        byte[] csvUserStats = csvService.getCsvUserStats();
        return generateResponse(csvUserStats, USERS_FILENAME);
    }

    @GetMapping("/bookings")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Получение статистики по бронированиям")
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
