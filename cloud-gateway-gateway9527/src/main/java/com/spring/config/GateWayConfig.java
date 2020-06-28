package com.spring.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZonedDateTime;

/**
 * @author Wangx
 * @create 2020/6/14
 * @since 1.0.0
 * 代码方式指定路由
 */
@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route_baidu", r -> r
                                .path("/directory")
                                .uri("https://www.douyu.com/directory/all")).build();
    }


    public static void main(String[] args) {
        System.out.println(ZonedDateTime.now());
    }
}
