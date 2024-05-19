package ru.skillbox.hotelbooking.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.skillbox.hotelbooking.dto.hotel.HotelCreateRequest;
import ru.skillbox.hotelbooking.dto.hotel.HotelDto;
import ru.skillbox.hotelbooking.dto.hotel.HotelRateRequest;
import ru.skillbox.hotelbooking.dto.hotel.HotelSearchRequest;
import ru.skillbox.hotelbooking.dto.hotel.HotelUpdateRequest;

/**
 * HotelService
 *
 * @author alex90bar
 */

public interface HotelService {

    HotelDto create(HotelCreateRequest request);

    HotelDto update(HotelUpdateRequest request);

    HotelDto getById(Long id);

    boolean deleteById(Long id);

    List<HotelDto> getAll();

    HotelDto rate(HotelRateRequest request);

    Page<HotelDto> find(Pageable pageable, HotelSearchRequest request);
}
