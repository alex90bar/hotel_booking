package ru.skillbox.hotelbooking.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.hotelbooking.dto.hotel.HotelCreateRequest;
import ru.skillbox.hotelbooking.dto.hotel.HotelDto;
import ru.skillbox.hotelbooking.dto.hotel.HotelUpdateRequest;
import ru.skillbox.hotelbooking.service.HotelService;

/**
 * HotelController
 *
 * @author alex90bar
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/hotel")
@Tag(name = "HotelController", description = "Работа с отелями")
public class HotelController {

    private final HotelService hotelService;

    @PostMapping("/create")
    @Operation(description = "Добавление нового отеля")
    public HotelDto createHotel(@Valid @RequestBody HotelCreateRequest hotelCreateRequest) {
        return hotelService.create(hotelCreateRequest);
    }

    @PutMapping("/update")
    @Operation(description = "Редактирование отеля")
    public HotelDto updateHotel(@Valid @RequestBody HotelUpdateRequest hotelUpdateRequest) {
        return hotelService.update(hotelUpdateRequest);
    }

    @GetMapping("/{id}")
    @Operation(description = "Поиск отеля по ИД")
    public HotelDto getById(@PathVariable("id") Long id) {
        return hotelService.getById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Удаление отеля по ИД")
    public boolean deleteById(@PathVariable("id") Long id) {
        return hotelService.deleteById(id);
    }

    @GetMapping("/all")
    @Operation(description = "Получение списка отелей")
    public List<HotelDto> getAll() {
        return hotelService.getAll();
    }

}
