package com.example.simplecrudapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "project_id")
    private List<Task> taskList = new ArrayList<>();

    @Version
    private Long version;

    public void addTask(Task task) {
        taskList.add(task);
    }
}
