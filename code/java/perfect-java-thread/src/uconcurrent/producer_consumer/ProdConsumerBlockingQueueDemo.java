package uconcurrent.producer_consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Kelly
 * @create 2020-04-26 15:19
 */
public class ProdConsumerBlockingQueueDemo {
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 生产线程启动");
            try {
                myResource.myProduct();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Product").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 消费线程启动");
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println();
        System.out.println("5 秒钟时间到，停止所有生产消费动作");

        myResource.stop();
    }

}
class MyResource{
    private volatile boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> queue = null;

    public MyResource(BlockingQueue<String> queue) {
        System.out.println(queue.getClass().getName());
        this.queue = queue;
    }

    public void myProduct() throws Exception {
        String data;
        boolean retValue;
        while (flag){
            data = atomicInteger.incrementAndGet() + "";
            retValue = queue.offer(data, 2L, TimeUnit.SECONDS);
            if (retValue){
                System.out.println(Thread.currentThread().getName() + " 插入阻塞队列 " + data + " 成功");
            } else {
                System.out.println(Thread.currentThread().getName() + " 插入阻塞队列 " + data + " 成功");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "\t 忽然暂停。。。flag = false 生产过程停止");
    }

    public void myConsumer() throws Exception {
        String result;
        while (flag){
            result = queue.poll(2, TimeUnit.SECONDS);
            if (result == null || result.equalsIgnoreCase("")){
                flag = false;
                System.out.println(Thread.currentThread().getName() + " 消费退出");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName() + " 消费 " + result + " 成功");
        }
    }

    public void stop(){
        flag = false;
    }
}