package com.spring.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;

/**
 * @author Wangx
 * @create 2020/7/20
 * @since 1.0.0
 * 自定义限流处理类
 */
public class CustomerBlockHandler {

    public static CommonResult handleException(BlockException exception) {
        return new CommonResult(200, "自定义限流，global", new Payment(2020L, "自定义方案1"));
    }

    public static CommonResult handleException2(BlockException exception) {
        return new CommonResult(200, "自定义限流，global", new Payment(2020L, "自定义方案2"));
    }
}
