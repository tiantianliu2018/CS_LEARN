package chapter08;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Kelly
 * @create 2020-05-25 17:09
 */
public class ExchangerTest {
    private static final Exchanger<String> exgr = new Exchanger<>();
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String A = "银行流水 A";
                try {
                    exgr.exchange(A);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String B = "银行流水 B";
                try {
                    String A = exgr.exchange("B");
                    System.out.println("A 和 B 数据是否一致：" + A.equals(B) + ", A 录入的是：" + A + ", B 录入的是：" + B);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
