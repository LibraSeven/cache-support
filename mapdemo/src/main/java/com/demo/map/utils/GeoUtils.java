package com.demo.map.utils;

/**
 * Created by louis on 2014/8/25.
 */
public class GeoUtils {
    /**
     * 计算两经纬度点之间的距离（单位：米）
     * @param lng1  经度
     * @param lat1  纬度
     * @param lng2
     * @param lat2
     * @return
     */
    public static double getDistance(double lng1,double lat1,double lng2,double lat2){
        double radLat1 = deg2rad(lat1);
        double radLat2 = deg2rad(lat2);
        double a = radLat1 - radLat2;
        double b = deg2rad(lng1) - deg2rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1)
                * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * 6378137.0;// 取WGS84标准参考椭球中的地球长半径(单位:m)
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    /**
     * 将角度转换为弧度
     * @param degree
     * @return
     */
    public static double deg2rad(double degree) {
        return degree * Math.PI/180.0;
    }

    /**
     * 将弧度转换为角度
     * @param radian
     * @return
     */
    public static double rad2deg(double radian) {
        return radian * 180 / Math.PI;
    }

    public static void main(String[] args) {
        System.out.println(getDistance(116.354917, 39.903488, 116.453083, 39.92651));
    }
}
