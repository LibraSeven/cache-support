package com.demo.map.utils;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created by louis on 2014/8/26.
 */
public class MongoManager {
    private DB db;
    public MongoManager(String host, int port, String database){
        try {
            MongoClient mongoClient=new MongoClient(new ServerAddress(host,port),new MongoClientOptions.Builder()
                    .socketTimeout(1000)
                    .connectionsPerHost(200)
                    .socketKeepAlive(true)
                    .build()
            );
            db=mongoClient.getDB(database);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void save(String collection, DBObject dbObject) {
        db.getCollection(collection).save(dbObject);
    }


}
