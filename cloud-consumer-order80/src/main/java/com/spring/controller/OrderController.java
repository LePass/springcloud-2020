package com.spring.controller;

import com.spring.lb.LoadBalancer;
import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author Wangx
 * @create 2020/5/22
 * @since 1.0.0
 */
@RestController
@Slf4j
/*
@RibbonClient(name = "cloud-order-service",configuration = com.spring.config.ApplicationContextConfig.class)
*/
public class OrderController {

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    //Ribbon 提供
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    //自定义 轮训算法 负载均衡
    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/consumer/payment/create")
    public CommonResult<Payment> creat(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        this.loadBalancerClient.choose("cloud-order-service"); //修改负载均衡策略
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB() {
        //获取到所有服务名为 cloud-payment-service 的服务
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        //轮训算法 得到具体的服务
        ServiceInstance instance = loadBalancer.instances(instances);
        //获取当前服务的 URL
        URI uri = instance.getUri();
        //调用服务
        return restTemplate.getForObject(uri+"/payment/lb", String.class);
    }
}
