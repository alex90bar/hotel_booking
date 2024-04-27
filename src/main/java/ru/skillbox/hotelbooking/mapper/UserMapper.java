package ru.skillbox.hotelbooking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.skillbox.hotelbooking.dto.user.UserCreateRequest;
import ru.skillbox.hotelbooking.dto.user.UserDto;
import ru.skillbox.hotelbooking.dto.user.UserUpdateRequest;
import ru.skillbox.hotelbooking.model.User;

/**
 * UserMapper
 *
 * @author alex90bar
 */

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserCreateRequest request);

    UserDto toDto(User user);

    void updateUserFromRequest(UserUpdateRequest request, @MappingTarget User user);
}
