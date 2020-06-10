package aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author Kelly
 * @create 2020-06-03 16:14
 */
public class MyBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("============ 前置通知 =============");
        System.out.println("当前执行方法：" + method.getName());
        System.out.println("当前执行方法的参数：" + args[0]);
        System.out.println("目标对象：" + target);
        System.out.println("当前执行方法的名称：" + method.getName() + " 作为日志记录");
        System.out.println("=================================");
    }
}
