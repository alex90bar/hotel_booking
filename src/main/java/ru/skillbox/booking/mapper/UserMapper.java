package ru.skillbox.booking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.skillbox.booking.dto.user.UserCreateRequest;
import ru.skillbox.booking.dto.user.UserDto;
import ru.skillbox.booking.dto.user.UserUpdateRequest;
import ru.skillbox.booking.model.User;

/**
 * UserMapper
 *
 * @author alex90bar
 */

@Mapper
public interface UserMapper {

    User toEntity(UserCreateRequest request);

    UserDto toDto(User user);

    void updateUserFromRequest(UserUpdateRequest request, @MappingTarget User user);
}
