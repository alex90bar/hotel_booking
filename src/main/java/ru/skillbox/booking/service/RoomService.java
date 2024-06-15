package ru.skillbox.booking.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.skillbox.booking.dto.room.RoomCreateRequest;
import ru.skillbox.booking.dto.room.RoomDto;
import ru.skillbox.booking.dto.room.RoomSearchRequest;
import ru.skillbox.booking.dto.room.RoomUpdateRequest;

/**
 * RoomService
 *
 * @author alex90bar
 */

public interface RoomService {

    RoomDto create(RoomCreateRequest request);

    RoomDto update(RoomUpdateRequest request);

    RoomDto getById(Long id);

    boolean deleteById(Long id);

    Page<RoomDto> find(Pageable pageable, RoomSearchRequest request);
}
