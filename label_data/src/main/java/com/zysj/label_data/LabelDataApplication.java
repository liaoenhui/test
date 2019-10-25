package com.zysj.label_data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class LabelDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabelDataApplication.class, args);
    }

}
