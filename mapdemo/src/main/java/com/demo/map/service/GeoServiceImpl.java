package com.demo.map.service;

import com.demo.map.dao.MongoDao;
import com.demo.map.entity.Point;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by louis on 2014/8/26.
 */
@Service
public class GeoServiceImpl implements GeoService {
    @Autowired
    private MongoDao mongoDao;
    @Override
    public List<DBObject> nearBy(Point center, int count) {
        String queryJson = String.format("{'loc':{$nearSphere:{$geometry:{type:'Point',coordinates:[%f,%f]}}}}", center.getLng(), center.getLat());
//        String queryJson = "{'loc':{$nearSphere:[" + center.getLng() + "," + center.getLat() + "]}}";
        DBObject query = (DBObject) JSON.parse(queryJson);
        return mongoDao.find("geo", query, null, null, count);
    }

    @Override
    public List<DBObject> nearByWithRange(Point center, double minDistance,double maxDistance) {
        String queryJson = String.format("{'loc':{$nearSphere:{$geometry:{type:'Point',coordinates:[%f,%f]},$minDistance:%f, $maxDistance:%f}}}", center.getLng(), center.getLat(),minDistance,maxDistance);
        DBObject query = (DBObject) JSON.parse(queryJson);
        return mongoDao.find("geo", query, null, null, Integer.MAX_VALUE);
    }
}
