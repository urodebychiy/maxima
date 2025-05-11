package com.koncha.maxima.mapping;

import com.koncha.maxima.DTO.TaskDTO;
import com.koncha.maxima.entity.Task;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskMapper {

    public TaskDTO toDTO(Task task) {
        return TaskDTO.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .build();
    }

    public Task toEntity(TaskDTO taskDTO) {
        return Task.builder()
                .title(taskDTO.getTitle())
                .description(taskDTO.getDescription())
                .build();
    }

    public List<TaskDTO> toDTOs(List<Task> tasks) {
        return tasks.stream()
                .map(this::toDTO)
                .toList();
    }

    public List<Task> toEntities(List<TaskDTO> taskDTOs) {
        return taskDTOs.stream()
                .map(this::toEntity)
                .toList();
    }
}
