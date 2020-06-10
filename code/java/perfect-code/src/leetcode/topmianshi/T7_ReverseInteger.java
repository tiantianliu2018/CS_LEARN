package leetcode.topmianshi;

/**
 * @author Kelly
 * @create 2020-05-22 10:48
 */
public class T7_ReverseInteger {
    public static void main(String[] args) {
        int reverse = reverse(123);
        System.out.println(reverse);
    }
    public static int reverse(int x) {
        int minVal = Integer.MIN_VALUE, maxVal = Integer.MAX_VALUE;
        long res = 0;
        while (x != 0){
            int t = x % 10;
            x /= 10;
            res = res * 10 + t;
            if (res < minVal || res > maxVal) return 0;
        }
        return (int) res;
    }
}
