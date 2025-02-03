package com.example.simplecrudapi.repository;

import com.example.simplecrudapi.model.User;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("SELECT u FROM user_table u where u.id = :id")
    Optional<User> findByIdForUpdate(Long id);
}
