package com.wx.example.service;

import org.springframework.stereotype.Service;

/**
 * Created by wangxiong on 2017/12/20.
 */
@Service
public class HelloService {

    /**
     * 启动的时候观察控制台是否打印此信息;
     */
    public HelloService() {
        System.out.println("HelloService.HelloService()");
        System.out.println("HelloService.HelloService()");
        System.out.println("HelloService.HelloService()");
    }

    public String getName(){
        return "hello";
    }

}
