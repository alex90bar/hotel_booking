package ru.skillbox.hotelbooking.service;

import ru.skillbox.hotelbooking.dto.user.UserCreateRequest;
import ru.skillbox.hotelbooking.dto.user.UserDto;
import ru.skillbox.hotelbooking.dto.user.UserUpdateRequest;

/**
 * UserService
 *
 * @author alex90bar
 */

public interface UserService {

    UserDto create(UserCreateRequest request);

    UserDto update(UserUpdateRequest request);

    UserDto getById(Long id);

    boolean deleteById(Long id);

    UserDto findUserByLogin(String login);

    boolean existsByEmailOrLogin(String email, String login);
}
