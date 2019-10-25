package com.zysj.task_mananage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.zysj.task_mananage.dao")
public class TaskMananageApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskMananageApplication.class, args);
    }

}
