package com.learn.test;

import com.learn.entity.User;
import com.learn.service.UserServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author Kelly
 * @create 2020-06-12 08:05
 */
public class TestUserService {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        UserServiceImpl userService = (UserServiceImpl) context.getBean("userService");
        List<User> users = userService.findAll();
        users.forEach(System.out::println);
        System.out.println("业务层的事务。。。");
        User user = new User("Kelly",23);
        userService.save(user);

        users = userService.findAll();
        users.forEach(System.out::println);

    }
}
