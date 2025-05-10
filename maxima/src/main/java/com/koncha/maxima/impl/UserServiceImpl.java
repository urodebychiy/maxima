package com.koncha.maxima.impl;

import com.koncha.maxima.DTO.UserDTO;
import com.koncha.maxima.entity.Task;
import com.koncha.maxima.entity.User;
import com.koncha.maxima.mapping.TaskMapper;
import com.koncha.maxima.mapping.UserMapper;
import com.koncha.maxima.repository.UserRepository;
import com.koncha.maxima.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TaskMapper taskMapper;

    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    //не судите строго, я моб
    @Transactional
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
        user.setUsername(userDTO.getUsername());
        user.getTasks().clear();
        List<Task> tasks = userDTO.getTasks()
                .stream()
                .map(taskMapper::toEntity)
                .toList();
        user.getTasks().addAll(tasks);
        User updatedUser = userRepository.save(user);
        return userMapper.toDTO(updatedUser);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDTOs(users);
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
        return userMapper.toDTO(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
        userRepository.delete(user);
    }
}
