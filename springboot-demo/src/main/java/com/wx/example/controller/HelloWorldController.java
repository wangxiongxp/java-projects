package com.wx.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangxiong on 2017/12/20.
 */

@RestController
public class HelloWorldController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public String hello(){
        logger.debug("Hello World!");
        return"Hello world!";
    }

}
