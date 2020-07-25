package com.spring;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Wangx
 * @create 2020/7/5
 * @since 1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain9001 {
    public static void main(String[] args) {
        //SpringApplication.run(PaymentMain9001.class, args);
        //自定义启动横幅
        SpringApplication application = new SpringApplication(PaymentMain9001.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
