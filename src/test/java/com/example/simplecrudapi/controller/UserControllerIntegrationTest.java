package com.example.simplecrudapi.controller;

import com.example.simplecrudapi.dto.UserDTO;
import com.example.simplecrudapi.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UserController.class)
@ExtendWith(SpringExtension.class)
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private UserService userService;

    @Test
    void should_return_user_by_id() throws Exception {
        // given
        var userDTO = UserDTO.builder()
                .id(1L)
                .zipCode("zip")
                .build();
        when(userService.findById(anyLong())).thenReturn(userDTO);

        // when && then
        mvc.perform(get("/api/users/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }
}