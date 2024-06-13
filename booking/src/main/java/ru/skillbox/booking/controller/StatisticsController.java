package ru.skillbox.booking.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.booking.client.StatisticsClient;

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

    private final StatisticsClient statisticsClient;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Получение статистики по зарегистрированным пользователям")
    public ResponseEntity<byte[]> getUsersCsv() {
        return generateResponse(statisticsClient.getUsersCsv());
    }

    @GetMapping("/bookings")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Получение статистики по бронированиям")
    public ResponseEntity<byte[]> getBookingsCsv() {
        return generateResponse(statisticsClient.getBookingsCsv());
    }

    private ResponseEntity<byte[]> generateResponse(ResponseEntity<byte[]> response) {
        if (response.getStatusCode() == HttpStatus.OK) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(response.getHeaders().getContentDisposition());
            headers.setContentType(response.getHeaders().getContentType());
            return new ResponseEntity<>(response.getBody(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


