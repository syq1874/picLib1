package com.yc.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class BaseMicroserviceConfig2 {

    public static void main(String[] args) {
        SpringApplication.run(BaseMicroserviceConfig2.class, args);
    }
}
