package com.example.simplecrudapi.mapper;

import com.example.simplecrudapi.dto.TaskDTO;
import com.example.simplecrudapi.model.Task;
import com.example.simplecrudapi.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskMapper {

    public static TaskDTO toTaskDTO(Task task) {
        return TaskDTO.builder()
                .id(task.getId())
                .description(task.getDescription())
                .name(task.getName())
                .status(task.getStatus())
                .userId(Optional.ofNullable(task.getUser())
                        .map(User::getId)
                        .orElse(null))
                .build();
    }

    public static Task toTask(TaskDTO taskDTO) {
        return Task.builder()
                .description(taskDTO.getDescription())
                .name(taskDTO.getName())
                .status(taskDTO.getStatus())
                .build();
    }
}
