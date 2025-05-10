package com.koncha.maxima.mapping;

import com.koncha.maxima.DTO.UserDTO;
import com.koncha.maxima.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserMapper {

    private final TaskMapper taskMapper;

    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .username(user.getUsername())
                .tasks(user.getTasks() != null
                        ? user.getTasks().stream()
                        .map(taskMapper::toDTO)
                        .collect(Collectors.toList())
                        : new ArrayList<>())
                .build();
    }

    public User toEntity(UserDTO userDto) {
        return User.builder()
                .username(userDto.getUsername())
                .tasks(userDto.getTasks() != null
                        ? userDto.getTasks().stream()
                        .map(taskMapper::toEntity)
                        .collect(Collectors.toList())
                        : new ArrayList<>())
                .build();
    }

    public List<UserDTO> toDTOs(List<User> users) {
        return users.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<User> toEntities(List<UserDTO> userDTOs) {
        return userDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
