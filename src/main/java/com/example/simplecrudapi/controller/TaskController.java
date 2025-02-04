package com.example.simplecrudapi.controller;

import com.example.simplecrudapi.dto.TaskDTO;
import com.example.simplecrudapi.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/")
    @Operation(summary = "Get all")
    public Page<TaskDTO> getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "50") Integer size) {
        var pageable = PageRequest.of(page, size);
        return taskService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get by id")
    public TaskDTO getById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @PostMapping("/")
    @Operation(summary = "Save")
    public TaskDTO save(@Valid @RequestBody TaskDTO body) {
        return taskService.save(body);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete by id")
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }
}
