package com.netintech.shiro.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties("shiro-session.redis-manager")
public class redisProperties {

    private String host ;

    private int port =6379;

    // 连接超时时间
    private int timeout;

    //  最大活动对象数
    private int poolMaxTotal =8;

    //  连接池中最大空闲连接数
    private int poolMaxIdle = 8;

    // 连接池资源耗尽时 调用者最大的阻塞时间
    private int poolMaxWait =5; //秒

    private int expireTime; // 过期时间



}
