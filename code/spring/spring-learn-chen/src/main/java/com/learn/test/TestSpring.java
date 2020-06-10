package com.learn.test;

import com.learn.service.DeptService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Kelly
 * @create 2020-06-03 22:23
 */
public class TestSpring {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("com/learn/spring.xml");
        DeptService deptService = (DeptService) context.getBean("deptService");
        System.out.println(deptService.getClass());
        deptService.find("小刘");
    }
}
