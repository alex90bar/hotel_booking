package ru.skillbox.booking.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.skillbox.booking.model.UserRole;

/**
 * UserDto
 *
 * @author alex90bar
 */

@Data
public class UserDto {

    @Schema(description = "Идентификатор", example = "5")
    private Long id;

    @Schema(description = "Имя пользователя", example = "aivanov77")
    private String login;

    @Schema(description = "Электронная почта", example = "ivanov77@gmail.com")
    private String email;

    @Schema(description = "Роль")
    private UserRole userRole;
}
