package basic.hashcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kelly
 * @create 2020-06-25 14:26
 *
 * 自定义类的对象作为 hashmap 的 key ，要重写 equals 和 hashcode 方法
 */
public class VerifyHashMapKey {
    public static void main(String[] args) {
        Map<Person, String> map = new HashMap<>();
        Person person = new Person();
        person.setName("Kelly");
        person.setAge(23);
        map.put(person, "姐姐");

        Person person2 = new Person();
        person2.setName("Tim");
        person2.setAge(20);
        map.put(person2, "弟弟");

        System.out.println(map.get(person));
        System.out.println(map.get(person2));

        Person person3 = new Person();
        person3.setName("Kelly");
        person3.setAge(23);
        System.out.println(map.get(person3));
    }
}
