package com.learn.kyra.tacos.message.rabbitmq;

import com.learn.kyra.tacos.domain.entity.Order;
import com.learn.kyra.tacos.message.jms.MessageConverter;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;

/**
 * @author liutiantian
 * @create 2020-04-11 14:57
 */
public class RabbitOrderReceiver {
    private RabbitTemplate rabbitTemplate;
    private MessageConverter converter;

    @Autowired
    public RabbitOrderReceiver(RabbitTemplate rabbitTemplate, MessageConverter converter) {
        this.rabbitTemplate = rabbitTemplate;
        this.converter = converter;
    }

    public Order receiveOrder(){
//        Message message = rabbitTemplate.receive("tacocloud.order.queue");
//        if (message != null){
//            return (Order) converter.fromMessage((javax.jms.Message) message);
//        }
//        return null;
        return rabbitTemplate.receiveAndConvert("tacocloud.order.queue",
                new ParameterizedTypeReference<Order>(){});
    }
}
