package com.netintech.shiro.config;



import com.netintech.shiro.properties.redisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


@EnableConfigurationProperties(redisProperties.class)
@Configuration
public class ShiroRedisAutoConfigure {

    @Autowired
    private  redisProperties redisProperties;

    //	配置redisconfig类，来配置jedisPool
    @Bean(name="jedisPool")
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "shiro-redis" ,name = "enable" ,havingValue = "true")
    public JedisPool jedisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisProperties.getPoolMaxIdle());
        jedisPoolConfig.setMaxTotal(redisProperties.getPoolMaxTotal());
        jedisPoolConfig.setMaxWaitMillis(redisProperties.getPoolMaxWait()*1000);
        JedisPool pool = new JedisPool( jedisPoolConfig,redisProperties.getHost(),redisProperties.getPort(),
                redisProperties.getTimeout()*1000);
        return pool;
    }


    public ShiroRedisAutoConfigure(redisProperties redisProperties){
        this.redisProperties = redisProperties;
    }


}
