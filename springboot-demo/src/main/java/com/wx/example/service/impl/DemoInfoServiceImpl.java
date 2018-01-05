package com.wx.example.service.impl;

import com.wx.example.bean.DemoInfo;
import com.wx.example.dao.DemoInfoRepository;
import com.wx.example.service.DemoInfoService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 启动 redis，可以使用后台服务启动 brew services start redis。
 * 或者直接启动：redis-server /usr/local/etc/redis.conf
 */

@Service
public class DemoInfoServiceImpl implements DemoInfoService{

    @Resource
    private DemoInfoRepository demoInfoRepository;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public void test(){
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("mykey4", "random1="+Math.random());
        System.out.println(valueOperations.get("mykey4"));
    }

    //keyGenerator="myKeyGenerator"
    @Cacheable(value="demoInfo") //缓存,这里没有指定key.
    @Override
    public DemoInfo findById(int id) {

        System.err.println("DemoInfoServiceImpl.findById()=========从数据库中进行获取的....id="+id);
        return demoInfoRepository.findOne(id);

    }

    @CacheEvict(value="demoInfo")
    @Override
    public void deleteFromCache(int id) {
        System.out.println("DemoInfoServiceImpl.delete().从缓存中删除.");
    }

}
