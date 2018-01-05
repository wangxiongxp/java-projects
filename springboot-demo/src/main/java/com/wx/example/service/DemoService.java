package com.wx.example.service;

import com.wx.example.bean.Demo;
import com.wx.example.dao.DemoDao;
import com.wx.example.dao.DemoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * Created by wangxiong on 2017/12/20.
 */
@Service
public class DemoService {

    @Resource
    private DemoRepository demoRepository;

    @Resource
    private DemoDao demoDao;

    @Transactional
    public void save(Demo demo){
        demoRepository.save(demo);
    }

    public Demo getById(int id){
        //demoRepository.findOne(id);//在demoRepository可以直接使用findOne进行获取.
        return demoDao.getById(id);
    }

}
