package chapter04;

/**
 * @author Kelly
 * @create 2020-05-24 11:32
 */
public class SynchronizedDemo {
    public static void main(String[] args) {
    // 对Synchronized Class对象进行加锁
        synchronized (SynchronizedDemo.class) { }
        // 静态同步方法，对Synchronized Class对象进行加锁
        m();
    }
    public static synchronized void m() {
    }
}
