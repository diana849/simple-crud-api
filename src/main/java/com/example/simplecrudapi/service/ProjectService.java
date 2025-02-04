package com.example.simplecrudapi.service;

import com.example.simplecrudapi.dto.ProjectDTO;
import com.example.simplecrudapi.exception.NotFoundException;
import com.example.simplecrudapi.mapper.ProjectMapper;
import com.example.simplecrudapi.repository.ProjectRepository;
import com.example.simplecrudapi.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ProjectService implements BaseService<ProjectDTO> {

    public static final String ERROR_MESSAGE = "Could not find project with id: ";

    private ProjectRepository projectRepository;
    private TaskRepository taskRepository;
    private ProjectMapper projectMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<ProjectDTO> findAll(Pageable pageable) {
        var projectPage = projectRepository.findAll(pageable);
        return projectPage.map(projectMapper::toProjectDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectDTO findById(Long id) {
        var project = projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ERROR_MESSAGE + id));
        return projectMapper.toProjectDTO(project);
    }

    @Override
    @Transactional
    public ProjectDTO save(ProjectDTO dto) {
        var project = projectRepository.save(projectMapper.toProject(dto));
        return projectMapper.toProjectDTO(project);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var project = projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ERROR_MESSAGE + id));

        projectRepository.delete(project);
    }

    @Transactional
    public void addTaskToProject(Long id, Long taskId) {
        var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException("Could not find task with id: " + taskId));

        var project = projectRepository.findByIdForUpdate(id)
                .orElseThrow(() -> new NotFoundException(ERROR_MESSAGE + id));

        project.addTask(task);
    }
}
