package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Wangx
 * @create 2020/5/25
 * @since 1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient  //该注解用使用consul或者zookeeper作为注册中心
public class PaymentMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8004.class, args);
    }
}
