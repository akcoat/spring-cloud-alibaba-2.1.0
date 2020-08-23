package com.netintech;



import com.alibaba.cloud.seata.GlobalTransactionAutoConfiguration;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
//(exclude = {DruidDataSourceAutoConfigure.class})
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class, GlobalTransactionAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@MapperScan("com.netintech.*.mapper")
public class AppModule1 {


    public static void main(String[] args) {
        SpringApplication.run(AppModule1.class, args);
    }
}
