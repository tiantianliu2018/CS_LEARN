package leetcode.multithread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * @author Kelly
 * @create 2020-06-25 10:18
 */
public class T1117_BuildingH2O {
    class H2O1 {
        private Semaphore hydrogen;
        private Semaphore oxygen;
        private CyclicBarrier barrier;

        public H2O1() {
            hydrogen = new Semaphore(2);
            oxygen = new Semaphore(1);
            barrier = new CyclicBarrier(3);
        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            hydrogen.acquire();
            try {
                barrier.await();
                releaseHydrogen.run();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            hydrogen.release();
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            oxygen.acquire();
            try {
                barrier.await();
                releaseOxygen.run();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            oxygen.release();
        }
    }
    /**
     * 信号量 + CyclicBarrier
     */
    class H2O {
        private Semaphore hydrogen;
        private Semaphore oxygen;
        private CyclicBarrier barrier;

        public H2O() {
            hydrogen = new Semaphore(2);
            oxygen = new Semaphore(1);
            barrier = new CyclicBarrier(3, new Runnable(){
                @Override
                public void run() {
                    hydrogen.release(2);
                    oxygen.release();
                }
            });
        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            hydrogen.acquire();
            releaseHydrogen.run();
            try {
                barrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            oxygen.acquire();
            releaseOxygen.run();
            try {
                barrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
