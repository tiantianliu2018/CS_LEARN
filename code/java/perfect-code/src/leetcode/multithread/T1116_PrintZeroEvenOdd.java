package leetcode.multithread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * @author Kelly
 * @create 2020-06-24 10:22
 *
 * 输入一个 n，打印出 1 - n，但是打印每一个数之前先打印 0
 * 0 奇数 0 偶数 0 奇数 ... 交替进行
 */
public class T1116_PrintZeroEvenOdd {
    // 信号量
    class ZeroEvenOdd {
        private int n;
        private Semaphore zero;
        private Semaphore even;
        private Semaphore odd;

        public ZeroEvenOdd(int n) {
            this.n = n;
            zero = new Semaphore(1);
            even = new Semaphore(0);
            odd = new Semaphore(0);
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                zero.acquire();
                printNumber.accept(0);
                if (i % 2 == 1) odd.release();
                else even.release();
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            for (int i = 2; i <= n; i += 2) {
                even.acquire();
                printNumber.accept(i);
                zero.release();
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i += 2) {
                odd.acquire();
                printNumber.accept(i);
                zero.release();
            }
        }
    }

    class ZeroEvenOdd1 {
        private int n;
        private Lock lock;
        private Condition zero;
        private Condition even;
        private Condition odd;
        private int flag;

        public ZeroEvenOdd1(int n) {
            this.n = n;
            lock = new ReentrantLock();
            zero = lock.newCondition();
            even = lock.newCondition();
            odd = lock.newCondition();
            flag = 0;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                lock.lock();
                try {
                    while (flag != 0) {
                        zero.await();
                    }
                    printNumber.accept(0);
                    if (i % 2 == 0){
                        flag = 2;
                        even.signal();
                    } else {   // 奇数
                        flag = 1;
                        odd.signal();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            for (int i = 2; i <= n ; i += 2) {
                lock.lock();
                try {
                    while (flag != 2){
                        even.await();
                    }
                    printNumber.accept(i);
                    flag = 0;
                    zero.signal();
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i += 2) {
                lock.lock();
                try {
                    while (flag != 1){
                        odd.await();
                    }
                    printNumber.accept(i);
                    flag = 0;
                    zero.signal();
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
