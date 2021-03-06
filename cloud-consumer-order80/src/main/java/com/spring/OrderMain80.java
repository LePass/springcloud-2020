package com.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Wangx
 * @create 2020/5/22
 * @since 1.0.0
 */
@SpringBootApplication
@EnableEurekaClient
/*
@RibbonClient(name = "cloud-order-service",configuration = com.myrule.MySelfRule.class)  //更改负载均衡策略
*/
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
