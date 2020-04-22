package com.learn.kyra.tacos.message.jms;

import com.learn.kyra.tacos.domain.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.GetMapping;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @author liutiantian
 * @create 2020-04-11 11:49
 */
public class JmsOrderMessagingService implements OrderMessagingService {
    private JmsTemplate jmsTemplate;
    private Destination orderQueue;

    @Autowired
    public JmsOrderMessagingService(JmsTemplate jmsTemplate, Destination orderQueue) {
        this.jmsTemplate = jmsTemplate;
        this.orderQueue = orderQueue;
    }

    @Override
    public void senOrder(Order order) {
        // jmsTemplate.send("tacocloud.order.queue", session -> session.createObjectMessage(order));
        jmsTemplate.convertAndSend("tacocloud.order.queue", order);
    }

    @GetMapping("/convertAndSend/order")
    public String convertAndSendOrder(Order order){
        jmsTemplate.convertAndSend("tacocloud.order.queue", order, this::addOrderSource);
        return "Convert and set Order";
    }

    private Message addOrderSource(Message message) throws JMSException {
        message.setStringProperty("X_ORDER_SOURCE", "WEB");
        return message;
    }


}
