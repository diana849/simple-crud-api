package com.example.simplecrudapi.controller;

import com.example.simplecrudapi.dto.BaseDTO;
import com.example.simplecrudapi.service.BaseService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseController<T extends BaseDTO> {

    private BaseService<T> baseService;

    @GetMapping("/")
    @Operation(summary = "Get all")

    public Page<T> getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "50") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return baseService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get by id")
    public T getById(@PathVariable Long id) {
        return baseService.findById(id);
    }

    @PostMapping("/")
    @Operation(summary = "Save")
    public T save(@Valid @RequestBody T body) {
        return baseService.save(body);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete by id")
    public void delete(@PathVariable Long id) {
        baseService.delete(id);
    }
}
