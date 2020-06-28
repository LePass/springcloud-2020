package com.spring.service;

import org.springframework.stereotype.Component;

/**
 * @author Wangx
 * @create 2020/6/12
 * @since 1.0.0
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_ok(Integer id) {
        return "解耦后 服务降级方法---paymentInfo_ok--- (┬＿┬)";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "解耦后 服务降级方法---paymentInfo_TimeOut--- (┬＿┬)";
    }
}
