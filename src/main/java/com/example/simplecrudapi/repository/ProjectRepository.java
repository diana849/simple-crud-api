package com.example.simplecrudapi.repository;

import com.example.simplecrudapi.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Override
    @Query("SELECT DISTINCT p FROM Project p LEFT JOIN FETCH p.taskList")
    Page<Project> findAll(Pageable pageable);
}
