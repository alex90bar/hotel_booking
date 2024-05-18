package ru.skillbox.hotelbooking.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
import ru.skillbox.hotelbooking.dto.user.UserCreateRequest;
import ru.skillbox.hotelbooking.dto.user.UserDto;
import ru.skillbox.hotelbooking.dto.user.UserUpdateRequest;
import ru.skillbox.hotelbooking.service.UserService;

/**
 * RoomController
 *
 * @author alex90bar
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@SecurityRequirement(name = "basicAuth")
@Tag(name = "UserController", description = "Работа с пользователями")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    @Operation(description = "Добавление пользователя")
    public UserDto createUser(@Valid @RequestBody UserCreateRequest request) {
        return userService.create(request);
    }

    @PutMapping("/update")
    @Operation(description = "Редактирование пользователя")
    public UserDto updateUser(@Valid @RequestBody UserUpdateRequest request) {
        return userService.update(request);
    }

    @GetMapping("/{id}")
    @Operation(description = "Поиск пользователя по ИД")
    public UserDto getById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Удаление пользователя по ИД")
    public boolean deleteById(@PathVariable("id") Long id) {
        return userService.deleteById(id);
    }
}
