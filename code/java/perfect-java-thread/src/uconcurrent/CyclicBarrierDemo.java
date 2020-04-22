package uconcurrent;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Kelly
 * @create 2020-04-21 18:18
 *
 * CyclicBarrier 循环栅栏，让一组线程到达一个屏障时被阻塞，
 * 最后一个线程到达屏障时，屏障才会开门
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, ()->{
            System.out.println("本关卡所有前置任务完成，开始游戏...");
        });

        new Thread(new PreTaskThread("加载地图数据", cyclicBarrier)).start();
        new Thread(new PreTaskThread("加载任务模型", cyclicBarrier)).start();
        new Thread(new PreTaskThread("加载背景音乐", cyclicBarrier)).start();

    }
    static class PreTaskThread implements Runnable{
        private String task;
        private CyclicBarrier cyclicBarrier;

        public PreTaskThread(String task, CyclicBarrier cyclicBarrier) {
            this.task = task;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            // 三个游戏关卡
            for (int i = 0; i < 3; i++) {
                try {
                    Random random = new Random();
                    Thread.sleep(random.nextInt(1000));
                    System.out.println(String.format("关卡 %d 的任务 %s 完成", i, task));
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                cyclicBarrier.reset(); // 重置屏障
            }
        }
    }
}
