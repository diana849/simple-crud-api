package com.example.simplecrudapi.controller;

import com.example.simplecrudapi.dto.ProjectDTO;
import com.example.simplecrudapi.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController extends BaseController<ProjectDTO> {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        super(projectService);
        this.projectService = projectService;
    }

    @PatchMapping("/{id}/task/{taskId}")
    @Operation(summary = "Add task to project")
    public void addTaskToProject(@PathVariable Long id, @PathVariable Long taskId) {
        projectService.addTaskToProject(id, taskId);
    }
}
