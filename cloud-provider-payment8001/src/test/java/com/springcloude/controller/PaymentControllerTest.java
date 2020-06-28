package com.springcloude.controller;

import com.springcloud.entities.Payment;
import com.springcloud.PaymentMain8001;
import com.springcloud.service.PaymentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * @author Wangx
 * @create 2020/5/21
 * @since 1.0.0
 */
@SpringBootTest(classes = PaymentMain8001.class)
@RunWith(SpringRunner.class)
public class PaymentControllerTest {

    private final String PAYMETN_URL = "http://localhost:8001/payment";

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void creat() {
        Payment payment = new Payment();
        payment.setSerial("cloud002");
        ResponseEntity<String> entity = restTemplate.postForEntity(PAYMETN_URL + "/create", payment, String.class);
        System.out.println(entity.getBody());
    }

    @Test
    public void getPaymentById() {
        String forEntity = restTemplate.getForObject(PAYMETN_URL + "/get/1", String.class);
        System.out.println(forEntity);
    }

}
