package uconcurrent;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author Kelly
 * @create 2020-04-21 17:31
 *
 * 学习信号量的 Demo
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        // 设置允许 3 个线程访问资源的信号量
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            new Thread(new MyThread(i, semaphore)).start();
        }
    }

    // 静态内部线程类
    private static class MyThread implements Runnable {
        private int value;
        private Semaphore semaphore;

        public MyThread(int value, Semaphore semaphore) {
            this.value = value;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                // 获取信号量
                semaphore.acquire();
                System.out.println(String.format("当前线程是 %d, 还剩 %d 个 资源，还有 %d 个线程在等待",
                        value, semaphore.availablePermits(), semaphore.getQueueLength()));
                // 睡眠随机事件，打乱释放顺序
                Random random = new Random();
                Thread.sleep(random.nextInt(1000));
                // 释放
                semaphore.release();
                System.out.println(String.format("线程 %d 释放了资源", value));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
