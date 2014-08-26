package com.demo.map.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by louis on 2014/8/26.
 */
@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping(value = "index.do",method = RequestMethod.GET)
    public String index(){
        return "index";
    }
}
