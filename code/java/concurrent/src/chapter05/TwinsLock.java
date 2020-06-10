package chapter05;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author Kelly
 * @create 2020-05-24 18:02
 */
public class TwinsLock implements Lock {
    private final Sync sync = new Sync(2);

    public static final class Sync extends AbstractQueuedSynchronizer{
        public Sync(int count) {
            if (count <= 0){
                throw new IllegalArgumentException("count must larger than zero.");
            }
            setState(count);
        }
        // 共享获取锁
        public int tryAcquireShared(int reduceCount){
            for (;;){  // 自旋
                int current = getState();
                int newCount = current - reduceCount;
                if (newCount < 0 || compareAndSetState(current, newCount)){
                    return newCount;
                }
            }
        }

        public boolean tryReleaseShared(int returnCount){
            for (;;){
                int current = getState();
                int newCount = current + returnCount;
                if (compareAndSetState(current, newCount)){
                    return true;
                }
            }
        }
    }
    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        int res = sync.tryAcquireShared(1);
        if (res >= 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,time);
    }



    @Override
    public Condition newCondition() {
        return null;
    }
}
