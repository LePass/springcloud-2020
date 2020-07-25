package com.spring.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author Wangx
 * @create 2020/7/10
 * @since 1.0.0
 */

@RestController
@Slf4j
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA() {
        //模拟运行业务需要800毫秒
        try {
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "------testA";

    }

    @GetMapping("/testB")
    public String testB() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(Thread.currentThread().getName() + "....testB");
        return "------testB";
    }

    @GetMapping("/testC")
    public String testC() {
        log.info("testC 测试异常比例");
        int age = 10 / 0;
        return "------testC";
    }

    @GetMapping("/testD")
    public String testD() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("testD 测试RT");
        return "------testD";
    }

    @GetMapping("/testE")
    public String testE() {
        log.info("testE  异常数");
        int age = 10 / 0;
        return "------testE";
    }

    /*
        @SentinelResource
              value  唯一标识
              blockHandler  调用兜底方法
     */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String  testHotKey(@RequestParam(value = "p1",required = false)String p1,
                              @RequestParam(value = "p2",required = false)String p2){
        return "------------testHotKey访问成功";
    }

    public String deal_testHotKey(String p1, String p2, BlockException blockException){
        //默认 兜底方法  Blocked by Sentinel (flow limiting)
        return "--------deal_testHotKey 兜底方法";
    }
}