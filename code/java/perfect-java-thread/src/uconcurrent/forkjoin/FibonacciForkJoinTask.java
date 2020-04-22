package uconcurrent.forkjoin;

import java.util.concurrent.*;

/**
 * @author Kelly
 * @create 2020-04-22 11:44
 *
 * Fork/Join 框架实现 Fibonacci 数列
 */
public class FibonacciForkJoinTask {
    static class Fibonacci extends RecursiveTask<Integer>{
        int n;

        public Fibonacci(int n){
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= 1){
                return n;
            } else {
                Fibonacci f1 = new Fibonacci(n - 1);
                f1.fork();
                Fibonacci f2 = new Fibonacci(n - 2);
                f2.fork();
                return f1.join() + f2.join();
            }
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println("CPU核数：" + Runtime.getRuntime().availableProcessors());

        long start = System.currentTimeMillis();
        Fibonacci fibonacci = new Fibonacci(40);
        Future<Integer> task = forkJoinPool.submit(fibonacci);
        System.out.println(task.get());

        long end = System.currentTimeMillis();
        System.out.println(String.format("耗时：%d millis", end - start));

        System.out.println("==============");

        long start2 = System.currentTimeMillis();
        int result = plainRecursion(40);
        long end2 = System.currentTimeMillis();
        System.out.println("计算结果:" + result);
        System.out.println(String.format("耗时：%d millis", end2 - start2));
    }

    // 普通递归，复杂度为O(2^n)
    public static int plainRecursion(int n) {
        if (n == 1 || n == 2) {
            return 1;
        } else {
            return plainRecursion(n - 1) + plainRecursion(n - 2);
        }
    }
}
