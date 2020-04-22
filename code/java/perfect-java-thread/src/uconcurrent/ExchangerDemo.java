package uconcurrent;

import java.util.concurrent.Exchanger;

/**
 * @author Kelly
 * @create 2020-04-21 17:49
 *
 * Exchanger 类，两个线程交换
 */
public class ExchangerDemo {
    public static void main(String[] args) throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(() -> {
            try {
                System.out.println("这是 " + Thread.currentThread().getName() + " ，得到了另一个线程的数据：" +
                        exchanger.exchange("这是来自线程 A 的数据"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-A").start();

        System.out.println("这个时候线程 A 是阻塞的，在等待线程 B 的数据");
        Thread.sleep(1000);

        new Thread(() -> {
            try {
                System.out.println("这是 " + Thread.currentThread().getName() + " ，得到了另一个线程的数据：" +
                        exchanger.exchange("这是来自线程 B 的数据"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-B").start();
    }
}
