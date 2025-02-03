package com.example.simplecrudapi.controller;

import com.example.simplecrudapi.dto.TaskDTO;
import com.example.simplecrudapi.service.TaskService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskController extends BaseController<TaskDTO> {

    public TaskController(TaskService taskService) {
        super(taskService);
    }
}
