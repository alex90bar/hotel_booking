package ru.skillbox.booking.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.booking.dto.room.RoomCreateRequest;
import ru.skillbox.booking.dto.room.RoomDto;
import ru.skillbox.booking.dto.room.RoomSearchRequest;
import ru.skillbox.booking.dto.room.RoomUpdateRequest;
import ru.skillbox.booking.exception.RoomNotFoundException;
import ru.skillbox.booking.mapper.RoomMapper;
import ru.skillbox.booking.model.Room;
import ru.skillbox.booking.repository.RoomRepository;
import ru.skillbox.booking.service.DatabaseService;
import ru.skillbox.booking.service.RoomService;
import ru.skillbox.booking.util.SpecificationUtil;

/**
 * RoomServiceImpl
 *
 * @author alex90bar
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final DatabaseService databaseService;
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Override
    public RoomDto create(RoomCreateRequest request) {
        databaseService.checkIfHotelExists(request.getHotelId());
        Room room = roomRepository.save(roomMapper.toEntity(request));
        return roomMapper.toDto(room);
    }

    @Override
    @Transactional
    public RoomDto update(RoomUpdateRequest request) {
        databaseService.checkIfHotelExists(request.getHotelId());
        roomRepository.findById(request.getId())
            .ifPresent(room -> roomMapper.updateRoomFromRequest(request, room));
        return getById(request.getId());
    }

    @Override
    public RoomDto getById(Long id) {
        return roomRepository.findById(id)
            .map(roomMapper::toDto)
            .orElseThrow(RoomNotFoundException::new);
    }

    @Override
    public boolean deleteById(Long id) {
        if (!roomRepository.existsById(id)) {
            throw new RoomNotFoundException();
        }
        databaseService.deleteBookingsByRoom(Room.builder().id(id).build());
        roomRepository.deleteById(id);
        return true;
    }

    @Override
    public Page<RoomDto> find(Pageable pageable, RoomSearchRequest request) {
        return roomRepository.findAll(
            Specification.where(SpecificationUtil.<Room, Long>fieldIsEqual("id", request.getId()))
                .and(SpecificationUtil.stringFieldLike("roomName", request.getRoomName()))
                .and(SpecificationUtil.integerIsMoreOrEqual("price", request.getMinPrice()))
                .and(SpecificationUtil.integerIsLessOrEqual("price", request.getMaxPrice()))
                .and(SpecificationUtil.fieldIsEqual("peopleCapacity", request.getPeopleCapacity()))
                .and(SpecificationUtil.isNotBookedInDateRange(request.getDtStart(), request.getDtEnd()))
                .and(SpecificationUtil.childFieldIsEqual("hotel", "id", request.getHotelId())), pageable)
            .map(roomMapper::toDto);
    }
}