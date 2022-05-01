package com.tars.capitalmarket.model;

import io.micronaut.serde.annotation.Serdeable;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Serdeable
@DynamoDbBean
public class InstrumentsMaster {

    String PK;
    String kiteToken;

    @DynamoDbPartitionKey
    public String getPK() {
        return PK;
    }


    public void setPK(String PK) {
        this.PK = PK;
    }

    @DynamoDbAttribute("user")
    public String getKiteToken() {
        return kiteToken;
    }

    public void setKiteToken(String kiteToken) {
        this.kiteToken = kiteToken;
    }

    @Override
    public String toString() {
        return "InstrumentsMaster{" +
                "PK='" + PK + '\'' +
                ", kiteToken='" + kiteToken + '\'' +
                '}';
    }
}