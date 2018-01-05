package com.wx.example.controller;

import com.wx.example.base.util.LocaleMessageSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by wangxiong on 2017/12/20.
 */

@Controller
@RequestMapping("/locale")
public class LocaleDemoController {

    @Autowired
    private MessageSource messageSource;

    @Resource
    private LocaleMessageSourceService localeMessageSourceService;

    /**
     * 模板文件进行国际化
     * @return
     */
    @RequestMapping(value="/demo",method= RequestMethod.GET)
    public String demo(){
        return "/localeDemo";
    }

    /**
     * 代码中获取国际化信息
     * @return
     */
    @RequestMapping(value="/welcome",method= RequestMethod.GET)
    public String getWelcome(HttpServletRequest request){

        //第一种方式是：
        Locale locale  = LocaleContextHolder.getLocale();
        //第二种方式是：
        Locale locale1 = RequestContextUtils.getLocale(request);

        String msg = messageSource.getMessage("welcome",null,locale);
        String msg2= messageSource.getMessage("welcome", null,locale1);

        System.out.println(msg);
        System.out.println(msg2);

        String msg3 = localeMessageSourceService.getMessage("welcome",locale);
        System.out.println(msg3);

        return "/localeDemo";
    }

    /**
     * 只针对会话的设置
     * @param request
     * @param lang
     * @return
     */
    @RequestMapping("/changeSessionLanguage")
    public String changeSessionLanguage(HttpServletRequest request,String lang){
        System.out.println(lang);
        if("zh".equals(lang)){
            //代码中即可通过以下方法进行语言设置
            request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,new Locale("zh","CN"));
        }else if("en".equals(lang)){
            //代码中即可通过以下方法进行语言设置
            request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,new Locale("en","US"));
        }
        return"redirect:/locale/demo";
    }

    /**
     * Cookie也会作用
     * @param request
     * @param response
     * @param lang
     * @return
     */
//    @RequestMapping("/changeSessionLanguage")
//    public String changeSessionLanguage(HttpServletRequest request, HttpServletResponse response, String lang){
//
//        System.out.println(lang);
//        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
//
//        if("zh".equals(lang)){
//            localeResolver.setLocale(request, response, new Locale("zh","CN"));
//        }else if("en".equals(lang)){
//
//            localeResolver.setLocale(request, response, new Locale("en","US"));
//        }
//        return "redirect:/locale/demo";
//    }


}
