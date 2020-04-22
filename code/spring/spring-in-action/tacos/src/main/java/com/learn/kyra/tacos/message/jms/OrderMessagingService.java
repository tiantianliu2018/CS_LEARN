package com.learn.kyra.tacos.message.jms;

import com.learn.kyra.tacos.domain.entity.Order;

/**
 * @author liutiantian
 * @create 2020-04-11 11:48
 */
public interface OrderMessagingService {
    void senOrder(Order order);
}
