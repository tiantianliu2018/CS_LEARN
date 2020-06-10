package chapter01;

/**
 * @author Kelly
 * @create 2020-05-13 15:38
 *
 * 死锁 Demo
 */
public class DeadLockDemo {
    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) {
        new DeadLockDemo().deadLock();
    }

    private void deadLock() {
        Thread t1 = new Thread(() -> {
           synchronized (A){
               try {
                   Thread.sleep(2000);  // 暂停一段时间，以使另一个线程拿到 B 的锁
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               synchronized (B){  // t1 线程等待 B 的锁
                   System.out.println("1");
               }
           }
        });

        Thread t2 = new Thread(() -> {
            synchronized (B){
                synchronized (A){   // t2 线程等待 A 的锁
                    System.out.println("2");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
