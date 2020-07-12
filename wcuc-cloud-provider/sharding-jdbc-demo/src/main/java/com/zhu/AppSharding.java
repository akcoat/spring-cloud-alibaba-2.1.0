package com.zhu;



import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(
        exclude = {
                DruidDataSourceAutoConfigure.class,
                DataSourceAutoConfiguration.class,
                MybatisPlusAutoConfiguration.class}
)
@MapperScan("com.zhu.*.mapper")
public class AppSharding {


    public static void main(String[] args) {
        SpringApplication.run(AppSharding.class,args);
    }
}
