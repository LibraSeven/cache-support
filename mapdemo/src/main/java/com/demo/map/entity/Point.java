package com.demo.map.entity;

import java.io.Serializable;

/**
 * Created by louis on 2014/8/26.
 */
public class Point implements Serializable{
    private double lng; // longitude
    private double lat; // latitude

    public Point(){}
    public Point(double lng,double lat){
        this.lng=lng;
        this.lat=lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
