package com.wx.example.controller;

import com.wx.example.bean.Demo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangxiong on 2017/12/20.
 */

/**
 * Spring Boot也是引用了JSON解析包Jackson，
 * 那么自然我们就可以在Demo对象上使用Jackson提供的json属性的注解，
 * 对时间进行格式化，对一些字段进行忽略等等
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/getDemo")
    public Demo getDemo(){
        Demo demo = new Demo();
        demo.setId(1);
        demo.setName("Jerry11");

        return demo;
    }

    @RequestMapping("/zeroException")
    public int zeroException(){
        return 100/0;
    }

}
