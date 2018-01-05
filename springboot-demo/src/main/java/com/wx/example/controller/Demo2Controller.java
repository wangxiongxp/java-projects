package com.wx.example.controller;

import com.wx.example.bean.Demo;
import com.wx.example.service.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by wangxiong on 2017/12/20.
 */
@RestController
@RequestMapping("/demo2")
public class Demo2Controller {

    @Resource
    private DemoService demoService;

    /**
     *测试保存数据方法.
     *@return
     */

    @RequestMapping("/save")
    public String save(){
        Demo demo = new Demo();
        demo.setName("Angel");
        demoService.save(demo);//保存数据.
        return"ok.Demo2Controller.save";
    }

    @RequestMapping("/getById")
    public Demo getById(int id){
        return demoService.getById(id);
    }

}
