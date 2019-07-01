package com.evanslaton.taskmaster;

import org.springframework.data.repository.CrudRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface TaskRepository extends CrudRepository<Task, String> {
    List<Task> findByTitle(String title);
}
