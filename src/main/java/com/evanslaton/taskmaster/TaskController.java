//package com.evanslaton.taskmaster;
//
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class TaskController {
//    private DynamoDBMapper dynamoDBMapper;
//
//    @Autowired
//    private AmazonDynamoDB amazonDynamoDB;
//
//    @Autowired
//    TaskRepository taskRepository;
//
//    @PostMapping(value="/")
//    public void addTask() {
//        Task t = new Task("Walk dog", "Walk the dog", "incomplete");
//        taskRepository.save(t);
//
//        Iterable<Task> l = taskRepository.findAll();
//        l.forEach(System.out::println);
//    }
//}
