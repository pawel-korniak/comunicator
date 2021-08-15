package com.pawelkorniak.comunicator.service;

import com.pawelkorniak.comunicator.model.ComunicatorUser;
import com.pawelkorniak.comunicator.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

@Service
@RequiredArgsConstructor
@DynamoDbBean
public class UserService {

    private final UserRepository userRepository;

    public ComunicatorUser save(ComunicatorUser user) {
        DynamoDbEnhancedClient enhancedClient = getDynamoDbEnhancedClient();
        createDynamoDBTable(enhancedClient);
        return userRepository.save(user);
    }

    private DynamoDbEnhancedClient getDynamoDbEnhancedClient() {
        Region region = Region.EU_WEST_1;
        DynamoDbClient ddb = DynamoDbClient.builder()
                .region(region)
                .build();
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(ddb)
                .build();
    }

    private void createDynamoDBTable(DynamoDbEnhancedClient enhancedClient) {
        DynamoDbTable<ComunicatorUser> customTable = enhancedClient.table("ComunicatorUser",
                TableSchema.fromBean(ComunicatorUser.class));
        ComunicatorUser user = new ComunicatorUser("login1","name1");
        customTable.putItem(user);
    }

    public ComunicatorUser getUserById(DynamoDbEnhancedClient enhancedClient) {
        //Create a DynamoDbTable object
        DynamoDbTable<ComunicatorUser> mappedTable = enhancedClient.table("ComunicatorUser",
                TableSchema.fromBean(ComunicatorUser.class));
        //Create a KEY object
        Key key = Key.builder()
                .partitionValue("login1")
                .build();
        // Get the item by using the key
        ComunicatorUser resultUser = mappedTable.getItem(r -> r.key(key));
        return resultUser;
    }

    public ComunicatorUser getUserById(String id) {
        return getUserById(getDynamoDbEnhancedClient());
    }
}
