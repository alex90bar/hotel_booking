package ru.skillbox.hotelbooking.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.skillbox.hotelbooking.model.UserRole;
import ru.skillbox.hotelbooking.validation.ValueOfEnum;

/**
 * UserUpdateRequest
 *
 * @author alex90bar
 */

@Data
public class UserUpdateRequest {

    @NotNull
    @Schema(description = "Идентификатор", example = "5")
    private Long id;

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
