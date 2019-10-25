package com.zysj.etl_manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = {"com.zysj.etl_manage.service", "com.zysj.etl_manage.controller"})
@MapperScan("com.zysj.etl_manage.dao")
@SpringBootApplication
public class EtlManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtlManageApplication.class, args);
    }

}
