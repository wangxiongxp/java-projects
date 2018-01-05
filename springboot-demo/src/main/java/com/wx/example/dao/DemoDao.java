package com.wx.example.dao;

import com.wx.example.bean.Demo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by wangxiong on 2017/12/20.
 */

@Repository
public class DemoDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public Demo getById(int id){
        String sql = "select * from user where id=?";
        RowMapper<Demo> rowMapper = new BeanPropertyRowMapper<>(Demo.class);
        return jdbcTemplate.queryForObject(sql,rowMapper,id);

    }

}
