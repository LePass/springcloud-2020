package com.spring.service;

import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @author Wangx
 * @create 2020/7/25
 * @since 1.0.0
 * 服务降级处理类
 */
@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444, "服务降级返回,---PaymentFallbackService", new Payment(id, "errorSerial"));
    }
}
