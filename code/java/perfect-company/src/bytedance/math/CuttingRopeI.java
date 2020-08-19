package bytedance.math;

/**
 * @author Kelly
 * @create 2020-06-27 23:18
 *
 * 将一根长度为 n 的绳子分为 m 段，求乘积的最大值 ln
 * 数学，推导出来绳子应该切成每段为 e 的长度，约为 2.7
 * 所以这里将绳子切分为 3 的时候，乘积最大
 */
public class CuttingRopeI {
    // 数学方法
    public int cuttingRope(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        int count3 = n / 3;
        int remain = n % 3;
        if (remain == 0) return (int) Math.pow(3, count3);
        if (remain == 1) return (int) Math.pow(3, count3 - 1) * 4;
        else return (int) Math.pow(3, count3) * 2;
    }

    /**
     * 动态规划，求最大乘积
     * 定义状态：dp[i] 表示长度为 i 的绳子的乘积最大值
     * 初始状态：dp[1] = 1, dp[2] = 1
     * 状态转移：dp[i] = max(dp[j] * [i - j], dp[i])
     */
    public int cuttingRope1(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                // j *（i - j) : j 先切出来的一块，i - j 不再进行切分
                // j * dp[i-j] : i - j 要再进行切分
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }

    public int cuttingRope2(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j, dp[j]) * (i - j));
            }
        }
        return dp[n];
    }

    /**
     * 剪绳子 II， 答案需要取模 1e9+7
     */
    public int cuttingRopeII(int n) {
        if (n < 4) return n - 1;
        long res = 1;
        while (n > 4){
            res *= 3;
            res %= 1000000007;
            n -= 3;
        }
        return (int) (res * n % 1000000007);
    }

}
