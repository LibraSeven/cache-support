package com.demo.map.controller;

import com.demo.map.entity.Point;
import com.demo.map.service.GeoService;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by louis on 2014/8/26.
 */
@Controller
@RequestMapping("/geo")
public class GeoController {
    @Autowired
    private GeoService geoService;

    @RequestMapping("/nearby.do")
    public String nearBy(){
        return "nearby";
    }
    @RequestMapping("/nearbyWithRange.do")
    public String nearbyWithRange(){
        return "nearbyWithRange";
    }
    @RequestMapping(value = "/getNearBy.do",method = RequestMethod.POST,produces ={"application/json; charset=utf-8"} )
    public @ResponseBody String getNearBy(@RequestParam Double lng,@RequestParam Double lat){
        List<DBObject> list=geoService.nearBy(new Point(lng,lat),50);
        return list.toString();
    }
    @RequestMapping(value = "/getNearByWithRange.do",method = RequestMethod.POST,produces ={"application/json; charset=utf-8"} )
    public @ResponseBody String getNearByWithRange(@RequestParam Double lng,@RequestParam Double lat,@RequestParam double minDistance,@RequestParam double maxDistance){
        List<DBObject> list=geoService.nearByWithRange(new Point(lng,lat),minDistance,maxDistance);
        return list.toString();
    }
}
