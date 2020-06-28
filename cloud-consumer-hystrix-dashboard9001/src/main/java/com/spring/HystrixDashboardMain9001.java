package com.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author Wangx
 * @create 2020/6/14
 * @since 1.0.0
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardMain9001  {

    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardMain9001.class, args);
    }
}
