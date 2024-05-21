package ru.skillbox.hotelbooking.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.hotelbooking.dto.room.RoomCreateRequest;
import ru.skillbox.hotelbooking.dto.room.RoomDto;
import ru.skillbox.hotelbooking.dto.room.RoomSearchRequest;
import ru.skillbox.hotelbooking.dto.room.RoomUpdateRequest;
import ru.skillbox.hotelbooking.service.RoomService;

/**
 * RoomController
 *
 * @author alex90bar
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
@SecurityRequirement(name = "basicAuth")
@Tag(name = "RoomController", description = "Работа с комнатами")
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Добавление новой комнаты")
    public RoomDto createRoom(@Valid @RequestBody RoomCreateRequest roomCreateRequest) {
        return roomService.create(roomCreateRequest);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Редактирование комнаты")
    public RoomDto updateRoom(@Valid @RequestBody RoomUpdateRequest roomUpdateRequest) {
        return roomService.update(roomUpdateRequest);
    }

    @GetMapping("/{id}")
    @Operation(description = "Поиск комнаты по ИД")
    public RoomDto getById(@PathVariable("id") Long id) {
        return roomService.getById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Удаление комнаты по ИД")
    public boolean deleteById(@PathVariable("id") Long id) {
        return roomService.deleteById(id);
    }

    @PostMapping("/find")
    @Operation(description = "Поиск комнат по фильтру, постранично")
    public Page<RoomDto> find(
        @ParameterObject Pageable pageable,
        @Valid @RequestBody RoomSearchRequest request
    ) {
        return roomService.find(pageable, request);
    }
}
