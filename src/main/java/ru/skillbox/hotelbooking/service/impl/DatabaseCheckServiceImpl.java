package ru.skillbox.hotelbooking.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skillbox.hotelbooking.exception.HotelNotFoundException;
import ru.skillbox.hotelbooking.repository.HotelRepository;
import ru.skillbox.hotelbooking.service.DatabaseCheckService;

/**
 * DatabaseCheckServiceImpl
 *
 * @author alex90bar
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class DatabaseCheckServiceImpl implements DatabaseCheckService {

    private final HotelRepository hotelRepository;

    @Override
    public void checkIfHotelExists(Long hotelId) {
        if (!hotelRepository.existsById(hotelId)) {
            throw new HotelNotFoundException();
        }
    }
}


