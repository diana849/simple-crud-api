package com.example.simplecrudapi.controller;

import com.example.simplecrudapi.dto.ProjectDTO;
import com.example.simplecrudapi.service.ProjectService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects")
public class ProjectController extends BaseController<ProjectDTO> {

    public ProjectController(ProjectService projectService) {
        super(projectService);
    }
}
