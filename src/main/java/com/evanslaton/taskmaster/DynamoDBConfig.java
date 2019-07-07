package com.evanslaton.taskmaster;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// https://stackoverflow.com/questions/41850104/what-is-the-alternative-for-amazondynamodbclient-that-got-deprecated
@Configuration
@EnableDynamoDBRepositories(basePackages = "com.evanslaton.taskmaster")
public class DynamoDBConfig {

    @Bean
    public AmazonDynamoDB amazonDynamoDB(AWSCredentialsProvider awsCredentialsProvider) {
        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(awsCredentialsProvider)
                .withEndpointConfiguration(new AwsClientBuilder
                        .EndpointConfiguration("https://dynamodb.us-west-2.amazonaws.com", "us-west-2"))
                .build();

        return amazonDynamoDB;
    }

    @Bean
    public AWSCredentialsProvider awsCredentialsProvider() {
        return new EnvironmentVariableCredentialsProvider();
    }
}