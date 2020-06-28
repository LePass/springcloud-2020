package com.spring.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author Wangx
 * @create 2020/6/1
 * @since 1.0.0
 * 自定义负载均衡算法  轮训算法
 */
public interface LoadBalancer {
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
