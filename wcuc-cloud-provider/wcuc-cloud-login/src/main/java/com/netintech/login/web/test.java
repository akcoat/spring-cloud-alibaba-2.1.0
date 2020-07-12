package com.netintech.login.web;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netintech.login.common.config.RedisConfig.RedisUtil;
import com.netintech.login.test.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RefreshScope
@DefaultProperties(defaultFallback = "fallback")
public class test {

    @Value("${file.uploadpath}")
    String uploadpath;


    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("test")
//    @HystrixCommand(fallbackMethod = "",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeout")
//    })
    @HystrixCommand
    public String test() {
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return uploadpath;
    }

    @GetMapping("testDataSource")
    public Object testDataSource() {
        List<Users> users = new Users().selectAll();
        return users;
    }

    @GetMapping("testRedis")
    @SentinelResource()
    public Object testRedis() {
        boolean set = redisUtil.set("k1", "v1");
        if (set)
            return "200";
        else
            return "500";
    }


    public String fallback() {

        return "hello world";
    }

}
