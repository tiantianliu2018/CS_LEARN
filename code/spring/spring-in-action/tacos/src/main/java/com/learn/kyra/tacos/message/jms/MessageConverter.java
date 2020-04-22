package com.learn.kyra.tacos.message.jms;


import org.springframework.jms.support.converter.MessageConversionException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @author liutiantian
 * @create 2020-04-11 12:01
 */
public interface MessageConverter {
    Message toMessage(Object object, Session session) throws JMSException, MessageConversionException;

    Object fromMessage(Message message);
}
