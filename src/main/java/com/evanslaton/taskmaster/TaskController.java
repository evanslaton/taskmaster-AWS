package com.evanslaton.taskmaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
