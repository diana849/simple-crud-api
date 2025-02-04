package com.example.simplecrudapi.controller;

import com.example.simplecrudapi.dto.ProjectDTO;
import com.example.simplecrudapi.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/")
    @Operation(summary = "Get all")
    public Page<ProjectDTO> getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "50") Integer size) {
        var pageable = PageRequest.of(page, size);
        return projectService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get by id")
    public ProjectDTO getById(@PathVariable Long id) {
        return projectService.findById(id);
    }

    @PostMapping("/")
    @Operation(summary = "Save")
    public ProjectDTO save(@Valid @RequestBody ProjectDTO body) {
        return projectService.save(body);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete by id")
    public void delete(@PathVariable Long id) {
        projectService.delete(id);
    }

    @PatchMapping("/{id}/task/{taskId}")
    @Operation(summary = "Add task to project")
    public void addTaskToProject(@PathVariable Long id, @PathVariable Long taskId) {
        projectService.addTaskToProject(id, taskId);
    }
}
