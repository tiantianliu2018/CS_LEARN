package com.learn.test;

import com.learn.entity.User;
import com.learn.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * @author Kelly
 * @create 2020-06-14 08:53
 */
public class TestUserService {
    private ClassPathXmlApplicationContext context;
    @Before
    public void Before(){
        this.context = new ClassPathXmlApplicationContext("spring.xml");
    }
    @Test
    public void testFindAll(){
        UserService userService = (UserService) context.getBean("userService");
        userService.findALl().forEach(System.out::println);
    }

    @Test
    public void testSave(){
        UserService userService = (UserService) context.getBean("userService");
        User user = new User();
        user.setBirthday(new Date());
        user.setName("Tina");
        user.setAge(23);
        userService.save(user);
    }
    @After
    public void after(){
        context.close();
    }
}
