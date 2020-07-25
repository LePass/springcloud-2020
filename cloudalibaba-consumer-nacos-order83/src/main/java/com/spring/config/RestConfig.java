package com.spring.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Wangx
 * @create 2020/7/6
 * @since 1.0.0
 */
@Configuration
public class RestConfig {
    @Bean
    @LoadBalanced //开启RestTemplate负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
