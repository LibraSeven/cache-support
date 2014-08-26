package com.demo.map.service;

import com.demo.map.entity.Point;
import com.mongodb.DBObject;

import java.util.List;

/**
 * Created by louis on 2014/8/26.
 */
public interface GeoService {

    public List<DBObject> nearBy(Point center,int count);

    /**
     *
     * @param center        center point
     * @param minDistance   unit:meter
     * @param maxDistance   unit:meter
     * @return              returns the documents from nearest to farthest
     */
    public List<DBObject> nearByWithRange(Point center,double minDistance,double maxDistance);
}
