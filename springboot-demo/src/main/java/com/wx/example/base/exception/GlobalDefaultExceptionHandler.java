package com.wx.example.base.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangxiong on 2017/12/20.
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public void defaultErrorHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        System.out.println("GlobalDefaultExceptionHandler.defaultErrorHandler()");
    }

    /*
    *返回json数据或者String数据：
    *那么需要在方法上加上注解：@ResponseBody
    *添加return即可。

    *返回视图：
    *定义一个ModelAndView即可，
    *然后return;
    *定义视图文件(比如：error.html,error.ftl,error.jsp);
    */

}