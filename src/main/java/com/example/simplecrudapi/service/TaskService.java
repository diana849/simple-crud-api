package com.example.simplecrudapi.service;

import com.example.simplecrudapi.dto.TaskDTO;
import com.example.simplecrudapi.exception.NotFoundException;
import com.example.simplecrudapi.mapper.TaskMapper;
import com.example.simplecrudapi.repository.TaskRepository;
import com.example.simplecrudapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService implements BaseService<TaskDTO> {

    private TaskRepository taskRepository;
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<TaskDTO> findAll(Pageable pageable) {
        var taskPage = taskRepository.findAll(pageable);
        return taskPage.map(TaskMapper::toTaskDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public TaskDTO findById(Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find task with id: " + id));
        return TaskMapper.toTaskDTO(task);
    }

    @Override
    @Transactional
    public TaskDTO save(TaskDTO dto) {
        var task = taskRepository.save(TaskMapper.toTask(dto));

        Optional.ofNullable(dto.getUserId())
                .ifPresent(userId -> {
                    var user = userRepository.findById(userId)
                            .orElseThrow(() -> new NotFoundException("Could not find user with id: " + userId));
                    task.setUser(user);
                });

        return TaskMapper.toTaskDTO(task);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find task with id: " + id));

        taskRepository.delete(task);
    }
}
