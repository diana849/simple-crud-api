package com.example.simplecrudapi.mapper;

import com.example.simplecrudapi.dto.TaskDTO;
import com.example.simplecrudapi.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(source = "user.id", target = "userId")
    TaskDTO toTaskDTO(Task task);

    Task toTask(TaskDTO taskDTO);
}
