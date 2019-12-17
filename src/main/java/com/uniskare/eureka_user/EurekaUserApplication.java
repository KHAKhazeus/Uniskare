package com.uniskare.eureka_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class EurekaUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaUserApplication.class, args);
    }

}
