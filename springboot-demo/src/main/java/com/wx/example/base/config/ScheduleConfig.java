package com.wx.example.base.config;

import com.wx.example.base.util.SpringUtil;
import com.wx.example.bean.Demo;
import com.wx.example.service.DemoService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

/**
 * Created by wangxiong on 2017/12/20.
 */
@Configuration
@EnableScheduling
public class ScheduleConfig {

    @Scheduled(cron = "0/20 * * * * ?") // 每20秒执行一次
    public void scheduler() {

        DemoService demoService = (DemoService)SpringUtil.getBean("demoService");
        Demo demo =  demoService.getById(1);
        System.out.println(demo.getName());

        System.out.println(">>>>>>>>> ScheduleConfig.scheduler()");

    }

}
