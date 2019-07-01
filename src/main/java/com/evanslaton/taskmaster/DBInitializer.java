package com.evanslaton.taskmaster;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;

public class DBInitializer {
    private DynamoDBMapper mapper;
    private DynamoDB client;

    CreateTableRequest tableRequest = mapper.generateCreateTableRequest(Task.class);

    tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

    Table table = client.createTable(request);

    table.waitForActive();
}
