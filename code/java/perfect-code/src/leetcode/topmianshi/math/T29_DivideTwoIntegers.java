package leetcode.topmianshi.math;

/**
 * @author Kelly
 * @create 2020-06-08 22:07
 *
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 */
public class T29_DivideTwoIntegers {
    /**
     * 除法的本质就是一个数可以被一个数减去多次
     */
    public int divide(int dividend, int divisor) {
        // 特判
        if (dividend == 0) return 0;
        if (divisor == 0) return -1;
        if (divisor == 1) return dividend;
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;  // 溢出
        if (divisor == -1) return -dividend;

        // 看结果的正负号，同号为正，异或运算，同号的话 sign = false，异号 sign = true
        boolean sign = (dividend > 0) ^ (divisor > 0);
        // 将两个数转为 long
        long ldividend = Math.abs((long) dividend);
        long ldivisor = Math.abs((long) divisor);
        long res = 0;
        while (ldividend >= ldivisor){  // 只要被除数大于除数
            long temp = ldivisor;
            long i = 1;
            while (ldividend >= temp){
                ldividend -= temp;  // 被除数减去除数
                res += i;  // 减了几次除数
                i <<= 1;
                temp <<= 1;  // 将除数左移，倍增除数
            }
        }
        if (sign) res = -res; // 异号
        if (res < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        if (res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        return (int) res;
    }
}
