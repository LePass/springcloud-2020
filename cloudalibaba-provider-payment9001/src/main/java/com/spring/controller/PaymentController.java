package com.spring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Wangx
 * @create 2020/7/6
 * @since 1.0.0
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") String id) {
        return "nacos registry, serverPort: " + serverPort + "\t id:" + id;
    }

    @RequestMapping("/job")
    public String getjob(@RequestBody Long[] id) {
        List<Long> collect = Arrays.stream(id).collect(Collectors.toList());
        System.out.println(collect);
        return "getJob";

    }
}
