package leetcode.multithread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Kelly
 * @create 2020-06-22 10:09
 * 设计修改程序, 以确保 two() 方法在 one() 方法之后被执行，three() 方法在 two() 方法之后被执行。
 *
 *  主要是在程序中增加屏障，限制并发的乱序执行
 */
public class T1114_PrintInOrder {
    // lock + condition 实现精准唤醒
    class Foo {
        private Lock lock;
        private Condition c2;
        private Condition c3;
        int state;

        public Foo() {
            lock = new ReentrantLock();
            c2 = lock.newCondition();
            c3 = lock.newCondition();
            state = 1;
        }

        public void first(Runnable printFirst) throws InterruptedException {

            // printFirst.run() outputs "first". Do not change or remove this line.
            lock.lock();
            try {
                printFirst.run();
                state = 2;
                c2.signal();
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

        public void second(Runnable printSecond) throws InterruptedException {

            // printSecond.run() outputs "second". Do not change or remove this line.
            lock.lock();
            try {
                while (state != 2){
                    c2.await();
                }
                printSecond.run();
                state = 3;
                c3.signal();

            } catch (Exception e){
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

        public void third(Runnable printThird) throws InterruptedException {

            // printThird.run() outputs "third". Do not change or remove this line.
            lock.lock();
            try {
                while (state != 3){
                    c3.await();
                }
                printThird.run();
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }

    // 信号量
    class Foo1{
        private Semaphore s2;
        private Semaphore s3;
        public Foo1() {
            s2 = new Semaphore(0);
            s3 = new Semaphore(0);
        }

        public void first(Runnable printFirst) throws InterruptedException {

            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            s2.release();
        }

        public void second(Runnable printSecond) throws InterruptedException {

            // printSecond.run() outputs "second". Do not change or remove this line.
            s2.acquire();
            printSecond.run();
            s3.release();
        }

        public void third(Runnable printThird) throws InterruptedException {

            // printThird.run() outputs "third". Do not change or remove this line.
            s3.acquire();
            printThird.run();
        }

    }

    // CountDownLatch
    class Foo2 {
        private CountDownLatch c2;
        private CountDownLatch c3;

        public Foo2() {
            c2 = new CountDownLatch(1);
            c3 = new CountDownLatch(1);
        }

        public void first(Runnable printFirst) throws InterruptedException {

            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            c2.countDown();
        }

        public void second(Runnable printSecond) throws InterruptedException {

            // printSecond.run() outputs "second". Do not change or remove this line.
            c2.await();
            printSecond.run();
            c3.countDown();
        }

        public void third(Runnable printThird) throws InterruptedException {

            // printThird.run() outputs "third". Do not change or remove this line.
            c3.await();
            printThird.run();
        }
    }

    // 无锁，volatile 修饰一个共享变量
    class Foo3 {
        private volatile int state;

        public Foo3() {
            state = 1;
        }

        public void first(Runnable printFirst) throws InterruptedException {

            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            state = 2;
        }

        public void second(Runnable printSecond) throws InterruptedException {

            // printSecond.run() outputs "second". Do not change or remove this line.
            while (state != 2){

            }
            printSecond.run();
            state = 3;
        }

        public void third(Runnable printThird) throws InterruptedException {

            // printThird.run() outputs "third". Do not change or remove this line.
            while (state != 3){

            }
            printThird.run();
        }
    }
}
