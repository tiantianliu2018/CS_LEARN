package com.learn.kyra.tacos.message.kafaka;

import com.learn.kyra.tacos.domain.entity.Order;
import com.learn.kyra.tacos.message.jms.OrderMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author liutiantian
 * @create 2020-04-11 15:14
 */
public class KafkaOrderMessagingService implements OrderMessagingService {
    private KafkaTemplate<String, Order> kafakaTemplate;

    @Autowired
    public KafkaOrderMessagingService(KafkaTemplate<String, Order> kafakaTemplate) {
        this.kafakaTemplate = kafakaTemplate;
    }

    @Override
    public void senOrder(Order order) {
        kafakaTemplate.send("tacocloud.orders.topic", order);

    }
}
