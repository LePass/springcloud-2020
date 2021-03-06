package com.springcloud.dao;

import com.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Wangx
 * @create 2020/5/21
 * @since 1.0.0
 */
@Mapper
public interface PaymentDao {
    /**
     * 新增
     * @param payment
     * @return
     */
    int create(Payment payment);

    /**
     * 根据id 查询
     * @param id
     * @return
     */
    Payment getPaymentById(@Param("id") Long id);
}
