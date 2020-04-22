package com.learn.kyra.tacos.message.jms;

import com.learn.kyra.tacos.domain.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.JMSException;

/**
 * @author liutiantian
 * @create 2020-04-11 11:13
 * 消息的拉模型
 */
@Profile("jms-template")
@Component("templateOrderReceiver")
public class JmsOrderReceiver implements OrderReceiver {

    private JmsTemplate jms;
    // private MessageConverter converter;

    public JmsOrderReceiver(JmsTemplate jms) {
        this.jms = jms;
        // this.converter = converter;
    }

    @Override
    public Order receiveOrder(){
        return (Order) jms.receiveAndConvert("tacocloud.order.queue");
//         return (Order) converter.fromMessage(message);
    }
}
