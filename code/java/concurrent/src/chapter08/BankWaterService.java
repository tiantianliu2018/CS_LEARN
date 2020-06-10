package chapter08;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author Kelly
 * @create 2020-05-25 16:56
 */
public class BankWaterService implements Runnable {
    // 设置四个屏障，处理完后执行当前类的 run 方法
    private CyclicBarrier c = new CyclicBarrier(4, this);
    // 创建四个线程
    private Executor executor = Executors.newFixedThreadPool(4);
    // 保存每个 sheet 计算出的结果
    private ConcurrentHashMap<String, Integer> sheetBankWaterCount = new ConcurrentHashMap<String, Integer>();
    private void count(){
        for (int i = 0; i < 4; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    sheetBankWaterCount.put(Thread.currentThread().getName(), 1);
                    // 计算完成插入一个屏障
                    try {
                        c.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
    @Override
    public void run() {
        int result = 0;
        for (Map.Entry<String, Integer> sheet : sheetBankWaterCount.entrySet()) {
            result += sheet.getValue();
        }
        // 将结果输出
        sheetBankWaterCount.put("result", result);
        System.out.println(result);
    }

    public static void main(String[] args) {
        BankWaterService bankWaterService = new BankWaterService();
        bankWaterService.count();
    }
}
