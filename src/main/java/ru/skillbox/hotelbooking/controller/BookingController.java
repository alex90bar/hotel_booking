package ru.skillbox.hotelbooking.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.hotelbooking.dto.booking.BookingCreateRequest;
import ru.skillbox.hotelbooking.dto.booking.BookingDto;
import ru.skillbox.hotelbooking.service.BookingService;

/**
 * BookingController
 *
 * @author alex90bar
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
@Tag(name = "BookingController", description = "Работа с бронированиями")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/create")
    @Operation(description = "Добавление нового бронирования")
    public BookingDto createBooking(@Valid @RequestBody BookingCreateRequest request) {
        return bookingService.create(request);
    }

    @GetMapping("/all")
    @Operation(description = "Получение всех бронирований")
    public List<BookingDto> getAll() {
        return bookingService.getAll();
    }
}
