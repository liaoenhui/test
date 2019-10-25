package com.zysj.ts.tsdata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.zysj.ts.tsdata.dao")
@EnableDiscoveryClient
public class TsDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(TsDataApplication.class, args);
    }

}
