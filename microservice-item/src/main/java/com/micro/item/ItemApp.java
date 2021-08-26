package com.micro.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author hkp
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.micro.item.controller", "com.micro.item.service","com.micro.item.config"})
public class ItemApp {
    public static void main(String[] args) {
        SpringApplication.run(ItemApp.class, args);
    }
}

