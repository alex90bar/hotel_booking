package ru.skillbox.booking.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.skillbox.booking.model.UserRole;
import ru.skillbox.booking.validation.ValueOfEnum;

/**
 * UserCreateRequest
 *
 * @author alex90bar
 */

@Data
public class UserCreateRequest {

    @NotBlank
    @Schema(description = "Имя пользователя", example = "aivanov77")
    private String login;

    @NotBlank
    @Schema(description = "Пароль", example = "password123")
    private String password;

    @NotBlank
    @Schema(description = "Электронная почта", example = "ivanov77@gmail.com")
    private String email;

    @NotNull
    @ValueOfEnum(enumClass = UserRole.class)
    @Schema(description = "Роль", example = "USER")
    private String userRole;
}
