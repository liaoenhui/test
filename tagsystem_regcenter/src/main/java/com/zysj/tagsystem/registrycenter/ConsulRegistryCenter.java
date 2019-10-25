package com.zysj.tagsystem.registrycenter;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@RestController
@RefreshScope
public class ConsulRegistryCenter implements CommandLineRunner {
    @Value("${test.username}")
    private String username;
    @Value("${test.password}")
    private String password;

    public static void main(String[] args) {
        SpringApplication.run(ConsulRegistryCenter.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("username:" + username);
        System.out.println("password:" + password);
    }

}

