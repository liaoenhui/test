package com.zysj.dssync;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.zysj.dssync.dao")
@ComponentScan({"com.zysj.ds","com.zysj.dssync"})
public class DsSyncApplication implements CommandLineRunner {
    @Value("${test.username}")
    private String username;
    @Value("${test.password}")
    private String password;
    public static void main(String[] args) {
        SpringApplication.run(DsSyncApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("username:" + username);
        System.out.println("password:" + password);
    }
}
