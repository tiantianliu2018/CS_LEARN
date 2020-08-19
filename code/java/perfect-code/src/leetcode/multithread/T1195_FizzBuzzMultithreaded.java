package leetcode.multithread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * @author Kelly
 * @create 2020-06-25 10:37
 */
public class T1195_FizzBuzzMultithreaded {
    // 信号量
    class FizzBuzz {
        private int n;
        private Semaphore number;
        private Semaphore fizz;
        private Semaphore buzz;
        private Semaphore fizzbuzz;

        public FizzBuzz(int n) {
            this.n = n;
            number = new Semaphore(0);
            fizz = new Semaphore(0);
            buzz = new Semaphore(0);
            fizzbuzz = new Semaphore(0);
        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
            for (int i = 3; i <= n; i++) {
                while (i % 3 == 0 && i % 5 != 0){
                    fizz.acquire();
                    printFizz.run();
                    number.release();
                }
            }
        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            for (int i = 5; i <= n; i++) {
                while (i % 3 != 0 && i % 5 == 0){
                    buzz.acquire();
                    printBuzz.run();
                    number.release();
                }
            }
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            for (int i = 15; i <= n; i++) {
                while (i % 3 == 0 && i % 5 == 0){
                    fizzbuzz.acquire();
                    printFizzBuzz.run();
                    number.release();
                }
            }
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                if (i % 3 == 0 && i % 5 == 0){
                    // 通知 fizzbuzz 线程，阻塞自己
                    fizzbuzz.release();
                    number.acquire();
                } else if (i % 3 == 0) {
                    fizz.release();
                    number.acquire();
                } else if (i % 5 == 0){
                    buzz.release();
                    number.acquire();
                } else {
                    printNumber.accept(i);
                }
            }
        }
    }

    // lock + condition
    class FizzBuzz1 {
        private int n;
        private Lock lock;
        private Condition fizz;
        private Condition buzz;
        private Condition fizzbuzz;
        private Condition number;
        private boolean state;

        public FizzBuzz1(int n) {
            this.n = n;
            lock = new ReentrantLock();
            fizz = lock.newCondition();
            buzz = lock.newCondition();
            fizzbuzz = lock.newCondition();
            number = lock.newCondition();
            state = true;   // 是否打印数字
        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
            for (int i = 3; i <= n; i += 3) {
                lock.lock();
                try {
                    if (i % 5 != 0){
                        while (state){
                            fizz.await();
                        }
                        printFizz.run();
                        state = true;
                        number.signal();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            for (int i = 5; i <= n; i += 5) {
                lock.lock();
                try {
                    if (i % 3 != 0){
                        while (state){
                            buzz.await();
                        }
                        printBuzz.run();
                        state = true;
                        number.signal();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            for (int i = 15; i <= n; i += 15) {
                lock.lock();
                try {
                    while (state){
                        fizzbuzz.await();
                    }
                    printFizzBuzz.run();
                    state = true;
                    number.signal();
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                lock.lock();
                try {
                    while (!state){
                        number.await();
                    }
                    if (i % 3 == 0 && i % 5 == 0) {
                        fizzbuzz.signal();
                        state = false;
                    } else if (i % 3 == 0) {
                        fizz.signal();
                        state = false;
                    } else if (i % 5 == 0) {
                        buzz.signal();
                        state = false;
                    } else {
                        printNumber.accept(i);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
