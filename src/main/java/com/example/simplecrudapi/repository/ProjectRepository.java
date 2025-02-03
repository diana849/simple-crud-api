package com.example.simplecrudapi.repository;

import com.example.simplecrudapi.model.Project;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Override
    @Query("SELECT DISTINCT p FROM Project p LEFT JOIN FETCH p.taskList")
    Page<Project> findAll(Pageable pageable);

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("SELECT p FROM Project p LEFT JOIN FETCH p.taskList where p.id = :id")
    Optional<Project> findByIdForUpdate(Long id);
}
