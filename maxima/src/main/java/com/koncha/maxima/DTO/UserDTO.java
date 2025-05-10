package com.koncha.maxima.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UserDTO {
    private String username;
    private List<TaskDTO> tasks;
}
