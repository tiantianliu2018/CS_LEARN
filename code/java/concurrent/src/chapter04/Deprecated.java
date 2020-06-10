package chapter04;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Kelly
 * @create 2020-05-24 11:18
 *
 * 暂停、恢复和停止操作对应线程
 */
public class Deprecated {
    public static void main(String[] args) throws InterruptedException {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Thread printThread = new Thread(new Runner(), "PrintThread");
        printThread.start();
        TimeUnit.SECONDS.sleep(3);

        printThread.suspend();  // 将 PrintThread 进行暂停，输出内容工作停止
        System.out.println("main thread suspend printThread at " + dateFormat.format(new Date()));

        TimeUnit.SECONDS.sleep(3);

        printThread.resume();  // 将暂停的线程恢复
        System.out.println("main thread resume printThread at " + dateFormat.format(new Date()));

        TimeUnit.SECONDS.sleep(3);

        // 将 PrintThread 进行终止，输出内容停止
        printThread.stop();
        System.out.println("main stop printThread at " + dateFormat.format(new Date()));

        TimeUnit.SECONDS.sleep(3);

    }

    private static class Runner implements Runnable {
        @Override
        public void run() {
            DateFormat format = new SimpleDateFormat("HH:mm:ss");
            while (true){
                System.out.println(Thread.currentThread().getName() + " runs as " + format.format(new Date()));
                SleepUtils.second(1);
            }
        }
    }
}
