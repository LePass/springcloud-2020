package com.spring.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Wangx
 * @create 2020/6/1
 * @since 1.0.0
 */
@Component
public class MyLb implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 自旋锁判断
     * @return
     */
    public final int getAndIncrement() {
        int current;
        int next;
        do {
            //取出当前原子类的 值
            current = this.atomicInteger.get();
            //记录当前用户访问的次数  如果大于等于int的最大值 则初始化为0 否则每访问一次 次数加1
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
            //  内存位置（V）、预期原值（A）->current  和新值(B)->next
            //  如果内存位置的值与预期原值相匹配，那么处理器会自动将该位置值更新为新值
        } while (!this.atomicInteger.compareAndSet(current, next));
        System.out.println("*****next:" + next);
        //返回当前用户访问的次数
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        //当前访问次数 取余  服务数量  得到余数（下标）
        int index = getAndIncrement() % serviceInstances.size();

        //根据得到的下标 得到具体的那个服务
        return serviceInstances.get(index);
    }
}
