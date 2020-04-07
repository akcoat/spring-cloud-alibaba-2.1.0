package com.netintech.login.web;

import com.netintech.login.common.config.RedisConfig.RedisUtil;
import com.netintech.login.test.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RefreshScope
public class test {

    @Value("${file.uploadpath}")
    String  uploadpath;


    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("test")
    public String test(){
        return uploadpath;
    }

    @GetMapping("testDataSource")
    public Object testDataSource(){
        List<Users> users = new Users().selectAll();
        return users;
    }

    @GetMapping("testRedis")
    public Object testRedis(){
        boolean set = redisUtil.set("k1", "v1");
        if(set)
            return "200";
        else
            return "500";
    }

}
