package com.example.simplecrudapi.controller;

import com.example.simplecrudapi.dto.ExtendedUserDTO;
import com.example.simplecrudapi.dto.UserDTO;
import com.example.simplecrudapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    @Operation(summary = "Get all")
    public Page<UserDTO> getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "50") Integer size) {
        var pageable = PageRequest.of(page, size);
        return userService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get by id")
    public UserDTO getById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/")
    @Operation(summary = "Save")
    public UserDTO save(@Valid @RequestBody UserDTO body) {
        return userService.save(body);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete by id")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/extended/{id}")
    @Operation(summary = "Get extended user data by id")
    public ExtendedUserDTO getExtendedUserById(@PathVariable Long id) {
        return userService.getExtendedUserById(id);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update user by id")
    public void updateUserById(@PathVariable Long id, @RequestParam String zipCode) {
        userService.updateUserById(id, zipCode);
    }
}
