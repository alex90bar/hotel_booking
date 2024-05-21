package ru.skillbox.hotelbooking.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.hotelbooking.dto.hotel.HotelCreateRequest;
import ru.skillbox.hotelbooking.dto.hotel.HotelDto;
import ru.skillbox.hotelbooking.dto.hotel.HotelRateRequest;
import ru.skillbox.hotelbooking.dto.hotel.HotelSearchRequest;
import ru.skillbox.hotelbooking.dto.hotel.HotelUpdateRequest;
import ru.skillbox.hotelbooking.exception.HotelNotFoundException;
import ru.skillbox.hotelbooking.mapper.HotelMapper;
import ru.skillbox.hotelbooking.model.Hotel;
import ru.skillbox.hotelbooking.model.Room;
import ru.skillbox.hotelbooking.repository.HotelRepository;
import ru.skillbox.hotelbooking.service.DatabaseService;
import ru.skillbox.hotelbooking.service.HotelService;
import ru.skillbox.hotelbooking.util.SpecificationUtil;

/**
 * HotelServiceImpl
 *
 * @author alex90bar
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final DatabaseService databaseService;
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
        databaseService.checkIfHotelExists(id);
        List<Room> roomsByHotel = databaseService.findRoomsByHotel(Hotel.builder().id(id).build());
        roomsByHotel.forEach(databaseService::deleteBookingsByRoom);
        databaseService.deleteRooms(roomsByHotel);
        hotelRepository.deleteById(id);
        return true;
    }

    @Override
    public List<HotelDto> getAll() {
        return hotelRepository.findAll().stream()
            .map(hotelMapper::toDto)
            .toList();
    }

    @Override
    public HotelDto rate(HotelRateRequest request) {
        Hotel hotel = hotelRepository.findById(request.getId()).orElseThrow(HotelNotFoundException::new);
        calculateRating(request, hotel);
        return hotelMapper.toDto(hotelRepository.save(hotel));
    }

    private void calculateRating(HotelRateRequest request, Hotel hotel) {
        Double rating = hotel.getRating();
        Integer newMark = request.getRating();
        Integer numberOfRating = hotel.getMarksCount();

        if (rating == null || numberOfRating == null) {
            hotel.setRating(newMark.doubleValue());
            hotel.setMarksCount(1);
        } else {
            double totalRating = rating * numberOfRating;
            totalRating = totalRating - rating + newMark;
            rating = totalRating / numberOfRating;

            double count = 10;
            rating = Math.round(rating * count) / count;

            numberOfRating++;

            hotel.setRating(rating);
            hotel.setMarksCount(numberOfRating);
        }
    }

    @Override
    public Page<HotelDto> find(Pageable pageable, HotelSearchRequest request) {
        return hotelRepository.findAll(
            Specification.where(SpecificationUtil.<Hotel, Long>fieldIsEqual("id", request.getId()))
            .and(SpecificationUtil.stringFieldLike("hotelName", request.getHotelName()))
            .and(SpecificationUtil.stringFieldLike("advertisementTitle", request.getAdvertisementTitle()))
            .and(SpecificationUtil.stringFieldLike("city", request.getCity()))
            .and(SpecificationUtil.stringFieldLike("address", request.getAddress()))
            .and(SpecificationUtil.fieldIsEqual("distanceToCityCenter", request.getDistanceToCityCenter()))
            .and(SpecificationUtil.fieldIsEqual("rating", request.getRating()))
            .and(SpecificationUtil.fieldIsEqual("marksCount", request.getMarksCount())), pageable)
            .map(hotelMapper::toDto);
    }
}
