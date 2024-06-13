package ru.skillbox.booking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * StatisticsClient
 *
 * @author alex90bar
 */

@FeignClient(name = "statistics", url = "${statistics.url}")
public interface StatisticsClient {

    @GetMapping("/getUsersCsv")
    ResponseEntity<byte[]> getUsersCsv();

    @GetMapping("/getBookingsCsv")
    ResponseEntity<byte[]> getBookingsCsv();
}
