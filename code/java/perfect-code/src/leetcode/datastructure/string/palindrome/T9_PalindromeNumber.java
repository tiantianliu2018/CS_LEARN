package leetcode.datastructure.string.palindrome;

/**
 * @author Kelly
 * @create 2020-04-22 22:36
 *
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 */
public class T9_PalindromeNumber {
    public static void main(String[] args) {
        boolean res = isPalindrome(121);
        System.out.println(res);
    }

    /**
     * 反转数字（通过取模），得到新的数字，判断是否和原来的相等
     */
    public static boolean isPalindrome(int x) {
        if (x == 0) return true;
        if (x < 0 || x % 10 == 0) return false;
        int reverseNum = 0;
        int tmp = x;
        while (tmp > 0){
            reverseNum = reverseNum * 10 + tmp % 10;
            tmp /= 10;
        }
        return reverseNum == x;
    }

    /**
     * 利用回文串的性质，只翻转一半的数字
     */
    public static boolean isPalindrome1(int x) {
        if (x == 0) return true;
        if (x < 0 || x % 10 == 0) return false;
        int right = 0;
        while (x > right){
            right = right * 10 + x % 10;
            x /= 10;
        }
        return (x == right) || (x == (right / 10));
    }
}
