package com.example.simplecrudapi.repository;

import com.example.simplecrudapi.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    User testUser;

    @BeforeEach
    public void setUp() {
        testUser = User.builder()
                .lastName("lastName")
                .email("email")
                .build();
        userRepository.save(testUser);
    }

    @Test
    void should_find_user_by_id() {
        // when
        var savedUser = userRepository.findById(testUser.getId()).orElse(null);

        // then
        assertNotNull(savedUser);
        assertEquals(testUser.getLastName(), savedUser.getLastName());
        assertEquals(testUser.getEmail(), savedUser.getEmail());
    }

    @AfterEach
    public void clean() {
        userRepository.delete(testUser);
    }
}
