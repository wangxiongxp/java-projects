package com.wx.example.controller;

import com.wx.example.bean.ValidatorDemo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by wangxiong on 2017/12/20.
 */

@Controller
@RequestMapping("/validator")
public class ValidatorDemoController {

    @RequestMapping(value="/demo",method= RequestMethod.GET)
    public String demo(Model model){
        model.addAttribute("validatorDemo",new ValidatorDemo());
        return "/validatorDemo";
    }

    @RequestMapping("/demoAdd")
    public String demoAdd(@Valid ValidatorDemo validatorDemo, BindingResult result, Model model){

        //有错误信息.
        model.addAttribute("validatorDemo",validatorDemo);
        if(result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            for(ObjectError  error:list){
                System.out.println(error.getCode()+"---"+error.getArguments()+"---"+error.getDefaultMessage());
            }
            return "/validatorDemo";
        }
        return "/validatorDemo";
    }

}
