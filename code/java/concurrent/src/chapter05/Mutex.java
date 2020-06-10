package chapter05;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author Kelly
 * @create 2020-05-24 17:25
 */
public class Mutex implements Lock {
    // 静态内部类，继承 AQS 实现自定义同步器
    private static class Sync extends AbstractQueuedSynchronizer{
        // 是否处于占用状态
         protected boolean isHeldExclusively(){
             return getState() == 1;   // AQS 中定义状态 private volatile int state;
         }
         public boolean tryAcquire(int acquires){
             // 如果状态为 0，将状态设为 1 将当前线程设为占用当前锁
             if (compareAndSetState(0, 1)){
                 setExclusiveOwnerThread(Thread.currentThread());
                 return true;
             }
             return false;
         }

         // 释放锁
         protected boolean tryRelease(int release){
              if (getState() == 0) throw new IllegalMonitorStateException();
              setExclusiveOwnerThread(null);
              setState(0);
              return true;
         }
         // 返回一个 Condition，每个 Condition 都包含了一个 condition 队列
         Condition newCondition(){
             return new ConditionObject();
         }
    }

    // 仅需要将操作代理到 Sync 上
    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
