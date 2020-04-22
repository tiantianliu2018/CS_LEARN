package uconcurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * @author Kelly
 * @create 2020-04-22 14:16
 *
 * Stream 多线程并行计算 demo
 */
public class StreamParallelDemo {
    public static void main(String[] args) {
        // sum();
        System.out.println(String.format("本计算机的核数：%d",
                Runtime.getRuntime().availableProcessors()));

        // 产生100w个随机数(1 ~ 100)，组成列表
        Random random = new Random();
        List<Integer> list = new ArrayList<>(1000_0000);

        for (int i = 0; i < 1000_0000; i++) {
            list.add(random.nextInt(100));
        }

        long prevTime = System.currentTimeMillis();
        list.stream().reduce((a, b) -> a + b).ifPresent(System.out::println);
        System.out.println(String.format("单线程计算耗时：%d", System.currentTimeMillis() - prevTime));

        prevTime = System.currentTimeMillis();
        list.stream().parallel().reduce((a, b) -> a + b).ifPresent(System.out::println);
        System.out.println(String.format("多线程计算耗时：%d", System.currentTimeMillis() - prevTime));
    }

    private static void sum() {
        Stream.of(1,2,3,4,5,6,7,8,9)
                .parallel()
                .reduce((a, b)->{
                    System.out.println(String.format("%s: %d + %d = %d",
                            Thread.currentThread().getName(), a, b, a + b));
                    return a + b;
                })
                .ifPresent(System.out::println);
    }
}

