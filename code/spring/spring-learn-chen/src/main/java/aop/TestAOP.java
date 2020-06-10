package aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Kelly
 * @create 2020-06-03 16:10
 */
public class TestAOP {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("aop/spring.xml");
        EmpService empService = (EmpService) context.getBean("empService");

        System.out.println(empService.getClass());
        empService.save("甜甜");
        empService.find("酸酸");
    }
}
