package com.zysj.label_manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@MapperScan("com.zysj.label_manage.dao")
@SpringBootApplication
@EnableFeignClients
public class LabelManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(LabelManageApplication.class, args);
    }
}
