package com.learn.test;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Kelly
 * @create 2020-06-10 07:38
 */
public class TestSqlSessionFactoryBean {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) context.getBean("sqlSessionFactory");
        System.out.println(sqlSessionFactory.openSession());
    }
}
