package chapter01;

/**
 * @author Kelly
 * @create 2020-05-13 15:21
 *
 * 并发与串行时间的比较
 */
public class ConcurrencyTime {
    private static final long count = 100000000l;  // 这个值：并发与串行的时间一样

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }

    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (int i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (int i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial: " + time + "ms, b = " + b + ", a = " + a);
    }
    // 并发实现的函数
    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(
                () -> {
                    int a = 0;
                    for (long i = 0; i < count; i++) {
                        a += 5;
                    }
                }
        );
        thread.start();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        thread.join();
        System.out.println("concurrency: " + time + "ms, b = " + b);
    }
}
