package leetcode.multithread;

import javafx.scene.control.SeparatorMenuItem;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Kelly
 * @create 2020-06-23 11:41
 */
public class T1115_PrintFooBarAlternately {
    // 信号量控制
    class FooBar {
        private int n;
        private Semaphore foo;
        private Semaphore bar;

        public FooBar(int n) {
            this.n = n;
            foo = new Semaphore(1);
            bar = new Semaphore(0);
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                foo.acquire();  // 此时 permit = 0
                printFoo.run();
                bar.release();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                bar.acquire();     // bar 的 permit 是 0， 阻塞
                printBar.run();
                foo.release();
            }
        }
    }

    // Lock + condition 精准唤醒
    class FooBar1 {
        private int n;
        private Lock lock;
        private Condition fooCondition;
        private Condition barCondition;
        private int state;

        public FooBar1(int n) {
            this.n = n;
            lock = new ReentrantLock();
            fooCondition = lock.newCondition();
            barCondition = lock.newCondition();
            state = 1;
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                lock.lock();
                try {
                    while (state != 1){
                        fooCondition.await();
                    }
                    printFoo.run();
                    barCondition.signal();
                    state = 2;
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                lock.lock();
                try {
                    while (state != 2){
                        barCondition.await();
                    }
                    printBar.run();
                    fooCondition.signal();
                    state = 1;
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class FooBar2 {
        private int n;
        private volatile boolean flag;

        public FooBar2(int n) {
            this.n = n;
            flag = true;
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                while (!flag){
                    Thread.yield();
                }
                printFoo.run();
                flag = false;
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                while (flag){
                    Thread.yield();
                }
                printBar.run();
                flag = true;
            }
        }
    }
}
