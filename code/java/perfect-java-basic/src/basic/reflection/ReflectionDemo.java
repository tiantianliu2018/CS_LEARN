package basic.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Kelly
 * @create 2020-05-28 11:40
 */
public class ReflectionDemo {
    public static void main(String[] args) throws Exception {
        // 获取 TargetObject Class 对象
        Class<?> targetClass = Class.forName("basic.reflection.TargetObject");
        TargetObject targetInstance = (TargetObject) targetClass.newInstance();
        /**
         * 获取所有类中所有定义的方法
         */
        Method[] methods = targetClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        /**
         * 获取指定方法，并调用
         */
        Method publicMethod = targetClass.getDeclaredMethod("publicMethod", String.class);
        publicMethod.invoke(targetInstance, "Java");

        /**
         * 调用 private 方法
         */
        Method privateMethod = targetClass.getDeclaredMethod("privateMethod");
        privateMethod.setAccessible(true);
        privateMethod.invoke(targetInstance);

        // 获取指定参数，并对参数进行修改
        Field value = targetClass.getDeclaredField("value");
        value.setAccessible(true);
        value.set(targetInstance,"Python");


         //  再次调用 private 方法
        privateMethod.invoke(targetInstance);
    }
}
