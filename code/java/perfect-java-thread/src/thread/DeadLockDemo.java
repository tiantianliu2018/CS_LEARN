package thread;

import java.util.concurrent.TimeUnit;

/**
 * @author Kelly
 * @create 2020-04-27 16:01
 *
 * 死锁 Demo
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "LockA";
        String lockB = "LockB";
        new Thread(new HoldLockThread(lockA, lockB), "ThreadAAA").start();
        new Thread(new HoldLockThread(lockB, lockA), "ThreadBBB").start();
    }
}
class HoldLockThread implements Runnable{
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + " 自己持有：" + lockA + "\t尝试获得: " + lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + " 自己持有：" + lockB + "\t尝试获得: " + lockA);
            }
        }
    }
}