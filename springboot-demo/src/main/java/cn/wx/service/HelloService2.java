package cn.wx.service;

import org.springframework.stereotype.Service;

/**
 * Created by wangxiong on 2017/12/24.
 */

@Service
public class HelloService2 {

    /**
     * 启动的时候观察控制台是否打印此信息;
     */
    public HelloService2(){
        System.out.println("HelloService2.HelloService2()");
        System.out.println("cn.wx.service.HelloService2.HelloService2()");
        System.out.println("HelloService2.HelloService2()");
    }

}
