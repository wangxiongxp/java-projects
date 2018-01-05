package com.wx.example.base.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 在项目服务启动的时候就去加载一些数据或做一些事情
 * Spring Boot应用程序在启动后，会遍历CommandLineRunner接口的实例并运行它们的run方法。
 * 也可以利用@Order注解（或者实现Order接口）来规定所有CommandLineRunner实例的运行顺序。
 */
@Component
@Order(value=2)
public class MyStartupRunner1 implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        //这里的args就是程序启动的时候进行设置的:
        //SpringApplication.run(App.class, new String[]{"hello,","Demo"});
        System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作11111111 <<<<<<<<<<<<<");
    }

}
