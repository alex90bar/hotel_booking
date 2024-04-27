package ru.skillbox.hotelbooking.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.hotelbooking.dto.hotel.HotelCreateRequest;
import ru.skillbox.hotelbooking.dto.hotel.HotelDto;
import ru.skillbox.hotelbooking.dto.hotel.HotelUpdateRequest;
import ru.skillbox.hotelbooking.exception.HotelNotFoundException;
import ru.skillbox.hotelbooking.mapper.HotelMapper;
import ru.skillbox.hotelbooking.model.Hotel;
import ru.skillbox.hotelbooking.repository.HotelRepository;
import ru.skillbox.hotelbooking.service.DatabaseCheckService;
import ru.skillbox.hotelbooking.service.HotelService;

/**
 * HotelServiceImpl
 *
 * @author alex90bar
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final DatabaseCheckService databaseCheckService;
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    @Override
    public HotelDto create(HotelCreateRequest hotelCreateRequest) {
        Hotel hotel = hotelRepository.save(hotelMapper.toEntity(hotelCreateRequest));
        return hotelMapper.toDto(hotel);
    }

    @Override
    @Transactional
    public HotelDto update(HotelUpdateRequest hotelUpdateRequest) {
        hotelRepository.findById(hotelUpdateRequest.getId())
            .ifPresent(hotel -> hotelMapper.updateHotelFromRequest(hotelUpdateRequest, hotel));
        return getById(hotelUpdateRequest.getId());
//        Hotel hotel = hotelRepository.findById(hotelUpdateRequest.getId())
//            .orElseThrow(HotelNotFoundException::new);
//        hotelMapper.updateHotelFromRequest(hotelUpdateRequest, hotel);
//        Hotel updated = hotelRepository.save(hotel);
//        return hotelMapper.toDto(updated);
    }

    @Override
    public HotelDto getById(Long id) {
        return hotelRepository.findById(id)
            .map(hotelMapper::toDto)
            .orElseThrow(HotelNotFoundException::new);
    }

    @Override
    public boolean deleteById(Long id) {
        databaseCheckService.checkIfHotelExists(id);
        hotelRepository.deleteById(id);
        return true;
    }

    @Override
    public List<HotelDto> getAll() {
        return hotelRepository.findAll().stream()
            .map(hotelMapper::toDto)
            .toList();
    }
}
