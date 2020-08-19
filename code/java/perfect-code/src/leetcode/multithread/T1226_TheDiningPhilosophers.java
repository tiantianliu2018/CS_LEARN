package leetcode.multithread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Kelly
 * @create 2020-06-25 11:21
 *
 * 哲学家进餐，如何避免死锁
 * 当 5 个哲学家都拿着其左边(或右边)的叉子时，会进入死锁。
 * 死锁产生的条件：
 *  1. 互斥条件：一个资源每次只能被一个进程使用，即在一段时间内某资源仅为一个进程所占有。此时若有其他进程请求该资源，则请求进程只能等待。
 *  2. 请求与保持条件：进程已经保持了至少一个资源，但又提出了新的资源请求，而该资源已被其他进程占有，此时请求进程被阻塞，但对自己已获得的资源保持不放。
 *  3. 不可剥夺条件:进程所获得的资源在未使用完毕之前，不能被其他进程强行夺走，即只能由获得该资源的进程自己来释放（只能是主动释放)。
 *  4. 循环等待条件: 若干进程间形成首尾相接循环等待资源的关系。
 */
public class T1226_TheDiningPhilosophers {
    // 打破循环等待条件，限制最多只允许四个哲学家持有叉子
    class DiningPhilosophers {
        private Lock[] locks;  // 对应 5 个叉子
        private Semaphore eatLimit = new Semaphore(4);  // 最多允许四个哲学家持有叉子

        public DiningPhilosophers() {
            locks = new ReentrantLock[]{
                    new ReentrantLock(),
                    new ReentrantLock(),
                    new ReentrantLock(),
                    new ReentrantLock(),
                    new ReentrantLock()
            };
        }

        // call the run() method of any runnable to execute its code
        public void wantsToEat(int philosopher,
                               Runnable pickLeftFork,
                               Runnable pickRightFork,
                               Runnable eat,
                               Runnable putLeftFork,
                               Runnable putRightFork) throws InterruptedException {
            int left = (philosopher + 1) % 5;
            int right = philosopher;

            eatLimit.acquire();
            locks[left].lock();
            locks[right].lock();
            pickLeftFork.run();
            pickRightFork.run();
            eat.run();
            putLeftFork.run();
            putRightFork.run();
            locks[left].unlock();
            locks[right].unlock();
            eatLimit.release();
        }
    }

    // 信号量
    class DiningPhilosophers1 {
        private Semaphore[] forkSemaphore;  // 对应 5 个叉子

        public DiningPhilosophers1() {
            forkSemaphore = new Semaphore[]{
                    new Semaphore(1),
                    new Semaphore(1),
                    new Semaphore(1),
                    new Semaphore(1),
                    new Semaphore(1)
            };
        }

        // call the run() method of any runnable to execute its code
        public void wantsToEat(int philosopher,
                               Runnable pickLeftFork,
                               Runnable pickRightFork,
                               Runnable eat,
                               Runnable putLeftFork,
                               Runnable putRightFork) throws InterruptedException {
            int left = (philosopher + 1) % 5;
            int right = philosopher;

            if (philosopher % 2 == 0) {
                forkSemaphore[left].acquire();
                forkSemaphore[right].acquire();
            } else {
                forkSemaphore[right].acquire();
                forkSemaphore[left].acquire();
            }

            pickLeftFork.run();
            pickRightFork.run();

            eat.run();

            putLeftFork.run();
            putRightFork.run();

            forkSemaphore[left].release();
            forkSemaphore[right].release();
        }
    }
}
