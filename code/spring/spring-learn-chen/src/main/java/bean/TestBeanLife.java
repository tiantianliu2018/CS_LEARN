package bean;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Kelly
 * @create 2020-06-04 10:07
 */
public class TestBeanLife {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean/spring.xml");
        // BeanFactory 容器一定要调用该方法进行 BeanPostProcessor 注册
//        factory.addBeanPostProcessor(new LifeCycleBean());
//
//        lifeCycleBean lifeCycleBean = (lifeCycleBean) factory.getBean("lifeCycle");
//        lifeCycleBean.display();
//
//        System.out.println("方法调用完成，容器开始关闭....");
//// 关闭容器
//        factory.destroySingletons();
    }
}
