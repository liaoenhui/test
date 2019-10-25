package com.zysj.etl_excute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class EtlExcuteApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtlExcuteApplication.class, args);
    }

}
