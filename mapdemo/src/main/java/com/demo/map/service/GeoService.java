package com.demo.map.service;

import com.demo.map.entity.Point;
import com.mongodb.DBObject;

import java.util.List;

/**
 * Created by louis on 2014/8/26.
 */
public interface GeoService {

    public List<DBObject> nearBy(Point center,int count);
}
