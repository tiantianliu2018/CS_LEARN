package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Kelly
 * @create 2020-04-26 15:45
 *
 * 实现 Callable 接口的线程
 */
public class CallableDemo {
    public static void main(String[] args) throws Exception {
        // 构造方法 FutureTask(Callable<V> callable) {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        Thread t1 = new Thread(futureTask, "AA");
        t1.start();
        System.out.println("********* " + futureTask.get());
    }
}

class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("--------- come in Callable");
        return 1024;
    }
}