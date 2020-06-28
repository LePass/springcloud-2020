package com.springcloud.service.impl;

import com.springcloud.entities.Payment;
import com.springcloud.dao.PaymentDao;
import com.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Wangx
 * @create 2020/5/21
 * @since 1.0.0
 */
@Service
public class PaymentServiceImpl  implements PaymentService {

    @Resource
    private PaymentDao paymentDao;


    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }


}
