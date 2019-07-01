package com.evanslaton.taskmaster;

import org.springframework.data.repository.CrudRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import java.util.List;
import java.util.UUID;

@EnableScan
public interface TaskRepository extends CrudRepository<Task, UUID> {
    List<Task> findById(String id);
}
