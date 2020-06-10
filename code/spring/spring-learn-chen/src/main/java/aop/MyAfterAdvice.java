package aop;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.ThrowsAdvice;

import java.beans.Expression;
import java.lang.reflect.Method;

/**
 * @author Kelly
 * @create 2020-06-07 08:19
 */
public class MyAfterAdvice implements AfterReturningAdvice, ThrowsAdvice {
    /**
     *
     * @param returnValue  目标方法返回值
     * @param method    当前执行方法对象
     * @param args      执行方法参数
     * @param target    目标对象
     * @throws Throwable
     */
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("========== 后置通知 ============");
        System.out.println("返回值："+ returnValue);
        System.out.println("方法名：" + method.getName());
        System.out.println("参 数：" + args[0]);
        System.out.println("目标对象：" + target);

    }

    public void afterThrowing(Method method, Object[] args, Object target, Expression expression){
        System.out.println("========== 异常通知 ===========");
    }
}
