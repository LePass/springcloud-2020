package com.springcloud.controller;

import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import com.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Wangx
 * @create 2020/5/21
 * @since 1.0.0
 */
@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    /**
     * 服务发现 获取服务信息
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("****插入结果****:" + result);
        if (result > 0) {

            return new CommonResult(200, "插入数据成功,serverPort：" + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据失败!");
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("****查询结果****:" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询数据成功,serverPort：" + serverPort, payment);
        } else {
            return new CommonResult(444, "查询数据失败!");
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        //获取服务 清单
        List<String> services = discoveryClient.getServices();
        services.forEach(element -> log.info("********element：" + element));
        //根据指定的服务名称 获取更详细的服务信息
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instances.forEach(instance -> log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getUri()));
        return this.discoveryClient;
    }


    /**
     * 测试自定义Ribbon 负载均衡是否生效
     *
     * @return
     */
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(HttpServletRequest request) {
        log.info("单一GatewayFilter 过滤器" + request.getHeader("X-Request-Name"));
        return "自定义Ribbon负载均衡算法=>" + serverPort;
    }


    /**
     * 模拟Feign调用超时 案例
     *
     * @return
     */
    @GetMapping(value = "/payment/sleep")
    public String paymentSleep() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "服务费执行超过Feign调用时间模拟:" + serverPort;
    }
}
