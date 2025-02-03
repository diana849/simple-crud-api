package com.example.simplecrudapi.controller;

import com.example.simplecrudapi.dto.ExtendedUserDTO;
import com.example.simplecrudapi.dto.UserDTO;
import com.example.simplecrudapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController<UserDTO> {

    private final UserService userService;

    public UserController(UserService userService) {
        super(userService);
        this.userService = userService;
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
