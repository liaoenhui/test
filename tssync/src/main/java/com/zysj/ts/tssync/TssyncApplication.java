package com.zysj.ts.tssync;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zysj.ts.tssync.dao")
public class TssyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(TssyncApplication.class, args);
    }

}
