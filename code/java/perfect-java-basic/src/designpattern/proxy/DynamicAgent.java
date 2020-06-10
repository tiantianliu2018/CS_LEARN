package designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Kelly
 * @create 2020-05-28 14:26
 */
public class DynamicAgent {
    static class MyHandler implements InvocationHandler{
        private Object proxy;

        public MyHandler(Object proxy) {
            this.proxy = proxy;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(">>>> before invoking");
            Object ret = method.invoke(this.proxy, args);
            System.out.println(">>>> after invoking");
            return ret;
        }
    }

    public static Object agent(Class interfaceClazz, Object proxy){
        return Proxy.newProxyInstance(interfaceClazz.getClassLoader(),  // 被代理类的类加载器
                new Class[]{interfaceClazz},   // 被代理类的接口数组
                new MyHandler(proxy));  // 调用处理器类的对象实例
    }
}
