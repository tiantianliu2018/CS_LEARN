package com.learn.test;

import com.learn.dao.UserDAO;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Kelly
 * @create 2020-06-11 08:03
 */
public class TestUserDAO {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        UserDAO userDAO = (UserDAO) context.getBean("userDAO");
        userDAO.findAll().forEach(System.out::println);
    }
}
