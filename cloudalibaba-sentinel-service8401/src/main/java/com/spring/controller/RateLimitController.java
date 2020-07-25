package com.spring.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.spring.myhandler.CustomerBlockHandler;
import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wangx
 * @create 2020/7/19
 * @since 1.0.0
 */
@RestController
public class RateLimitController {

    //按资源名称限流
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource() {
        return new CommonResult(200, "按资源名称限流测试OK", new Payment(2020L, "serial001"));
    }

    public CommonResult handleException(BlockException exception) {
        return new CommonResult(444, exception.getClass().getCanonicalName() + "\t 服务不可用");
    }


    //按照Url地址限流
    @GetMapping(value = "/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl() {
        return new CommonResult(200, "按照Url地址限流测试OK", new Payment(2020L, "serial002"));
    }


    //自定义处理逻辑限流
    @GetMapping(value = "/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
                    blockHandlerClass = CustomerBlockHandler.class,blockHandler = "handleException")
    public  CommonResult customerBlockHandler(){
        return new CommonResult(200,"按用户自定义限流测试OK",new Payment(2020L,"serial003"));

    }
}
