package com.evanslaton.taskmaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
public class TaskController {
    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return (List<Task>) taskRepository.findAll();
    }

    @PostMapping("/tasks")
    public void createTask(@RequestBody Task task) {
        taskRepository.save(task);
    }

    @PutMapping("/tasks/{id}/state")
    public Task updateTaskStatus(@PathVariable UUID id) {
        Task task = taskRepository.findById(id).get();
        task.updateStatus();
        taskRepository.save(task);
        return task;
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable UUID id) {
        taskRepository.deleteById(id);
    }
}
