package com.example.simplecrudapi.service;

import com.example.simplecrudapi.dto.ProjectDTO;
import com.example.simplecrudapi.exception.NotFoundException;
import com.example.simplecrudapi.mapper.ProjectMapper;
import com.example.simplecrudapi.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ProjectService implements BaseService<ProjectDTO> {

    private ProjectRepository projectRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<ProjectDTO> findAll(Pageable pageable) {
        var projectPage = projectRepository.findAll(pageable);
        return projectPage.map(ProjectMapper::toProjectDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectDTO findById(Long id) {
        var project = projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find project with id: " + id));
        return ProjectMapper.toProjectDTO(project);
    }

    @Override
    @Transactional
    public ProjectDTO save(ProjectDTO dto) {
        var project = projectRepository.save(ProjectMapper.toProject(dto));
        return ProjectMapper.toProjectDTO(project);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var project = projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find project with id: " + id));

        projectRepository.delete(project);
    }
}
