package com.pawelkorniak.comunicator.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@DynamoDbBean
public class ComunicatorUser {

    @DynamoDbPartitionKey
    public String getLogin() {
        return login;
    }

    String login;
    String name;
    LocalDateTime joinDate;
    LocalDateTime lastLoginDate;

    public ComunicatorUser(String login, String name) {
        this.login = login;
        this.name = name;
        this.joinDate = LocalDateTime.now();
        this.lastLoginDate = this.joinDate;
    }
}
