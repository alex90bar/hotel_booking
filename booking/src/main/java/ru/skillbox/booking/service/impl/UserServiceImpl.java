package ru.skillbox.booking.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.booking.dto.user.UserCreateRequest;
import ru.skillbox.booking.dto.user.UserDto;
import ru.skillbox.booking.dto.user.UserUpdateRequest;
import ru.skillbox.booking.exception.UserAlreadyExistException;
import ru.skillbox.booking.exception.UserNotFoundException;
import ru.skillbox.booking.mapper.UserMapper;
import ru.skillbox.booking.model.User;
import ru.skillbox.booking.repository.UserRepository;
import ru.skillbox.booking.service.UserService;

/**
 * UserServiceImpl
 *
 * @author alex90bar
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final DatabaseServiceImpl databaseCheckService;

    @Override
    public UserDto create(UserCreateRequest request) {
        if (existsByEmailOrLogin(request.getEmail(), request.getLogin())) {
            throw new UserAlreadyExistException();
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User user = userRepository.save(userMapper.toEntity(request));
        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public UserDto update(UserUpdateRequest request) {
        userRepository.findById(request.getId())
            .ifPresent(user -> {
                checkLoginAndEmailChange(request, user);
                userMapper.updateUserFromRequest(request, user);
            });
        return getById(request.getId());
    }

    private void checkLoginAndEmailChange(UserUpdateRequest request, User user) {
        String oldLogin = user.getLogin();
        String newLogin = request.getLogin();
        if (!oldLogin.equals(newLogin) && userRepository.existsByLogin(newLogin)) {
            throw new UserAlreadyExistException();
        }

        String oldEmail = user.getEmail();
        String newEmail = request.getEmail();
        if (!oldEmail.equals(newEmail) && userRepository.existsByEmail(newEmail)) {
            throw new UserAlreadyExistException();
        }
    }

    @Override
    public UserDto getById(Long id) {
        return userRepository.findById(id)
            .map(userMapper::toDto)
            .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public boolean deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException();
        }
        databaseCheckService.deleteBookingsByUser(User.builder().id(id).build());
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public UserDto findUserByLogin(String login) {
        return userRepository.findUserByLogin(login)
            .map(userMapper::toDto)
            .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public boolean existsByEmailOrLogin(String email, String login) {
        return userRepository.existsByEmailOrLogin(email, login);
    }
}


