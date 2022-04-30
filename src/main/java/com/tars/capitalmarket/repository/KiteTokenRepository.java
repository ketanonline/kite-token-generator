package com.tars.capitalmarket.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tars.capitalmarket.model.InstrumentsMaster;
import com.zerodhatech.models.User;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

import java.io.IOException;

@Singleton
public class KiteTokenRepository {
    static final Logger logger = LoggerFactory.getLogger(KiteTokenRepository.class);
    private final DynamoDbAsyncTable<InstrumentsMaster> instrmentsMasterTable;

    @Inject
    public DynamoDbAsyncClient dynamoDbAsyncClient;
    private DynamoDbEnhancedAsyncClient enhancedAsyncClient;
    private ObjectMapper objectMapper;

    public KiteTokenRepository(DynamoDbAsyncClient dynamoDbAsyncClient, ObjectMapper objectMapper) {
        this.dynamoDbAsyncClient = dynamoDbAsyncClient;
        this.enhancedAsyncClient = DynamoDbEnhancedAsyncClient.builder().dynamoDbClient(dynamoDbAsyncClient).build();
        this.objectMapper = objectMapper;
        this.instrmentsMasterTable = enhancedAsyncClient.table("InstrumentsMaster", TableSchema.fromBean(InstrumentsMaster.class));
    }

    public void save(User token) {

        logger.info("Saving token: " + token);

        // Create a Record to save

        try {
            InstrumentsMaster record = new InstrumentsMaster();
            record.setPK("KiteToken");
            record.setKiteToken(objectMapper.writeValueAsString(token));
            instrmentsMasterTable.updateItem(record);
        } catch (IOException e) {
            e.printStackTrace();
        }


//        HashMap<String, AttributeValue> itemKey = new HashMap<String,AttributeValue>();
//
//        itemKey.put("PK", AttributeValue.builder().s("KiteToken").build());
//
//        HashMap<String, AttributeValueUpdate> updatedValues =
//                new HashMap<String,AttributeValueUpdate>();
//
//        // Update the column specified by name with updatedVal
//        updatedValues.put("EpochRunTime", AttributeValueUpdate.builder()
//                .value(AttributeValue.builder().n(runTimeInEpoch.toString()).build())
//                .action(AttributeAction.PUT)
//                .build());
//
//        updatedValues.put("LastRunTime", AttributeValueUpdate.builder()
//                .value(AttributeValue.builder().s(epochToIso8601(runTimeInEpoch)).build())
//                .action(AttributeAction.PUT)
//                .build());
//        // Update to the table
//        try {
//            mappedTable.updateItem(record).get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
        logger.info("Kite Token updated to DB");

    }
}
