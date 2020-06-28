package com.spring.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Wangx
 * @create 2020/5/25
 * @since 1.0.0
 */
@RestController
@Slf4j
public class OrderController {

    public static final String INVOKE_URL  = "http://cloud-provider-payment";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/zk")
    public String OrderInfo(){
        log.info(INVOKE_URL+"/payment/zk");
        return  restTemplate.getForObject(INVOKE_URL +"/payment/zk",String.class);
    }





}
