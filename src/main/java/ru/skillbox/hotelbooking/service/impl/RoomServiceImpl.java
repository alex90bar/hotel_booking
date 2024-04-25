package ru.skillbox.hotelbooking.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.hotelbooking.dto.room.RoomCreateRequest;
import ru.skillbox.hotelbooking.dto.room.RoomDto;
import ru.skillbox.hotelbooking.dto.room.RoomUpdateRequest;
import ru.skillbox.hotelbooking.exception.RoomNotFoundException;
import ru.skillbox.hotelbooking.mapper.RoomMapper;
import ru.skillbox.hotelbooking.model.Room;
import ru.skillbox.hotelbooking.repository.RoomRepository;
import ru.skillbox.hotelbooking.service.DatabaseCheckService;
import ru.skillbox.hotelbooking.service.RoomService;

/**
 * RoomServiceImpl
 *
 * @author alex90bar
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final DatabaseCheckService databaseCheckService;
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Override
    public RoomDto create(RoomCreateRequest request) {
        databaseCheckService.checkIfHotelExists(request.getHotelId());
        Room room = roomRepository.save(roomMapper.toEntity(request));
        return roomMapper.toRoomDto(room);
    }

    @Override
    @Transactional
    public RoomDto update(RoomUpdateRequest request) {
        databaseCheckService.checkIfHotelExists(request.getHotelId());
        roomRepository.findById(request.getId())
            .ifPresent(room -> roomMapper.updateRoomFromRequest(request, room));
        return getById(request.getId());
    }

    @Override
    public RoomDto getById(Long id) {
        return roomRepository.findById(id)
            .map(roomMapper::toRoomDto)
            .orElseThrow(RoomNotFoundException::new);
    }

    @Override
    public boolean deleteById(Long id) {
        if (!roomRepository.existsById(id)) {
            throw new RoomNotFoundException();
        }
        roomRepository.deleteById(id);
        return true;
    }

}