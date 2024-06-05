package ru.skillbox.commonresources.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCreatedEvent {

    private Long id;
    private String login;

}
