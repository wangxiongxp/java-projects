package com.wx.example.dao;

import com.wx.example.bean.Demo;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by wangxiong on 2017/12/20.
 */
public interface DemoRepository extends CrudRepository<Demo,Integer> {

}
