package com.netintech.login;

import com.alibaba.cloud.seata.GlobalTransactionAutoConfiguration;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class, GlobalTransactionAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.netintech.login.*.mapper")
@EnableHystrix
public class AppLoginServer {
    public static void main(String[] args) {
        SpringApplication.run(AppLoginServer.class, args);
    }


}
