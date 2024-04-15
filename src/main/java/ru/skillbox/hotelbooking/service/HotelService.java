package ru.skillbox.hotelbooking.service;

import java.util.List;
import ru.skillbox.hotelbooking.dto.hotel.HotelCreateRequest;
import ru.skillbox.hotelbooking.dto.hotel.HotelDto;
import ru.skillbox.hotelbooking.dto.hotel.HotelUpdateRequest;

/**
 * HotelService
 *
 * @author alex90bar
 */

public interface HotelService {

    HotelDto create(HotelCreateRequest request);

    HotelDto update(HotelUpdateRequest hotelUpdateRequest);

    HotelDto getById(Long id);

    boolean deleteById(Long id);

    List<HotelDto> getAll();
}
