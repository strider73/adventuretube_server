package com.adventuretube;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoTemplateConfiguration {
    @Bean
    MongoClient mongoClient() {
        return MongoClients.create("mongodb://rootuser:rootpass@mongodb/adventuretube?authSource=admin");
    }

    @Bean
    MongoOperations mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, "adventuretube");
    }

}
