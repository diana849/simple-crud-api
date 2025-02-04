package com.example.simplecrudapi.mapper;

import com.example.simplecrudapi.dto.ProjectDTO;
import com.example.simplecrudapi.model.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectDTO toProjectDTO(Project project);

    Project toProject(ProjectDTO projectDTO);
}
