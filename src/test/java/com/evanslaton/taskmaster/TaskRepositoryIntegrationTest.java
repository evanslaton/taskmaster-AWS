package com.evanslaton.taskmaster;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DynamoDBConfig.class)
@WebAppConfiguration
@ActiveProfiles("local")
public class TaskRepositoryIntegrationTest {
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    TaskRepository taskRepository;

    private static final String EXPECTED_TITLE = "Walk Dog";
    private static final String EXPECTED_DESCRIPTION = "Walk the dog";
    private static final String EXPECTED_STATUS = "incomplete";

    @Before
    public void setup() throws Exception {
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
        CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Task.class);
        tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

//        Add this line if you need to create a table
//        amazonDynamoDB.createTable(tableRequest);
        dynamoDBMapper.batchDelete(taskRepository.findAll());
    }

    @Test
    public void sampleTestCase() {
        Task walkDog = new Task(EXPECTED_TITLE, EXPECTED_DESCRIPTION, EXPECTED_STATUS);
        taskRepository.save(walkDog);

        List<Task> result = (List<Task>) taskRepository.findAll();

        assertTrue("Not empty", result.size() > 0);
        assertTrue("Contains item with expected cost",
                result.get(0).getTitle().equals(EXPECTED_TITLE));
    }
}
