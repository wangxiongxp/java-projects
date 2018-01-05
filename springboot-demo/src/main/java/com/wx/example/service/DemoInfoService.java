package com.wx.example.service;

import com.wx.example.bean.DemoInfo;

/**
 * Created by wangxiong on 2017/12/24.
 */
public interface DemoInfoService {

    public DemoInfo findById(int id);

    public void deleteFromCache(int id);

    void test();

}
