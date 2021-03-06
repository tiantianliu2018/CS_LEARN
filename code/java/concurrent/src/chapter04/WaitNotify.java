package chapter04;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Kelly
 * @create 2020-05-24 11:42
 * 等待通知的 Demo
 */
public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();
    }

    private static class Wait implements Runnable{
        @Override
        public void run() {
            synchronized (lock){
                while (flag){
                    try {
                        System.out.println(Thread.currentThread().getName() + " flag is true. wait @" +
                                new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 满足条件，完成工作
                System.out.println(Thread.currentThread().getName() + " flag is false. running @" +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    private static class Notify implements Runnable {
        @Override
        public void run() {
            synchronized (lock){
                System.out.println(Thread.currentThread().getName() + " hold lock. Notify @" +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));

                lock.notifyAll();
                flag = false;
                SleepUtils.second(5);
            }
            synchronized (lock){
                System.out.println(Thread.currentThread().getName() + " hold lock again. Sleep @" +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUtils.second(5);
            }
        }
    }
}
