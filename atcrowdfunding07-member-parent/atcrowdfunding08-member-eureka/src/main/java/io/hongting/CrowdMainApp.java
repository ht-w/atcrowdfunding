package io.hongting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author hongting
 * @create 2021 10 14 2:27 PM
 */
@EnableEurekaServer
@SpringBootApplication
public class CrowdMainApp {
    public static void main(String[] args) {
        SpringApplication.run(CrowdMainApp.class, args);
    }
}
