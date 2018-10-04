package com.example.demo;

import com.mongodb.MongoClient;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

public class MongoPersistTests {


    @Test
    public void addData() {
        //async mongo client is abstract and can not be instantiate so here using in sync client
        MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient(), "test"));
    }
}
