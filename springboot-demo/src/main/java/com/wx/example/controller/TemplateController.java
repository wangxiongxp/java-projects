package com.wx.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * 模板引擎
 * thymeleaf和freemarker是可以共存的
 * 默认情况下，freemarker视图优先级会高于thymeleaf
 */
@Controller
public class TemplateController {

    //从 application.properties 中读取配置，如取不到默认值为 fromTemplateController.helloJsp
    @Value("${application.hello:fromTemplateController.helloJsp}")
    private String hello;

   /**
    * 返回html模板.
    */
    @RequestMapping("/helloHtml")
    public String helloThyme(Map<String,Object> map){
        map.put("hello","fromTemplateController.helloHtml");
        return "/helloHtml";
    }

    @RequestMapping("/helloFtl")
    public String helloFree(Map<String,Object>map){
        map.put("hello","fromTemplateController.helloFtl");
        return "/helloFtl";
    }

    @RequestMapping("/helloJsp")
    public String helloJsp(Map<String,Object>map){
        map.put("hello",hello);
        return "helloJsp";
    }

}
