package designpattern.proxy;

/**
 * @author Kelly
 * @create 2020-05-28 14:33
 */
public class ReflectTest {
    public static void main(String[] args) {
        Fruit fruit = (Fruit) DynamicAgent.agent(Fruit.class, new Apple());
        fruit.show();
    }
}
