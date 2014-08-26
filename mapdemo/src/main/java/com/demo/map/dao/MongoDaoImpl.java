package com.demo.map.dao;

import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by louis on 14-8-16.
 */
@Repository
public class MongoDaoImpl implements MongoDao {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public DBObject findOne(String collection, DBObject query, DBObject fields) {
        return mongoTemplate.getCollection(collection).findOne(query,fields);
    }

    @Override
    public List<DBObject> find(String collection, DBObject query, DBObject fields, DBObject orderBy, int pageNum, int pageSize) {
        return mongoTemplate.getCollection(collection).find(query,fields).skip((pageNum-1)*pageSize).limit(pageSize).sort(orderBy).toArray();
    }

    @Override
    public List<DBObject> find(String collection, DBObject query, DBObject fields, DBObject orderBy, int limit) {
        return mongoTemplate.getCollection(collection).find(query,fields).sort(orderBy).limit(limit).toArray();
    }

    @Override
    public void delete(String collection, DBObject dbObject) {
        mongoTemplate.getCollection(collection).remove(dbObject);
    }

    @Override
    public void save(String collection, DBObject dbObject) {
        mongoTemplate.getCollection(collection).save(dbObject);
    }

    @Override
    public void update(String collection, DBObject query, DBObject update, boolean upsert, boolean multi) {
        mongoTemplate.getCollection(collection).update(query, update, upsert, multi);
    }

    @Override
    public Long count(String collection, DBObject query) {
        return mongoTemplate.getCollection(collection).count(query);
    }
}
