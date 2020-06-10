package chapter04;

import java.util.concurrent.TimeUnit;

/**
 * @author Kelly
 * @create 2020-05-23 20:40
 */
public class SleepUtils {
    public static final void second(long second){
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
