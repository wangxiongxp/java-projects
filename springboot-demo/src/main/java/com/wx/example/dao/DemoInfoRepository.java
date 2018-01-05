package com.wx.example.dao;

import com.wx.example.bean.DemoInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by wangxiong on 2017/12/20.
 */
public interface DemoInfoRepository extends CrudRepository<DemoInfo,Integer> {

}
