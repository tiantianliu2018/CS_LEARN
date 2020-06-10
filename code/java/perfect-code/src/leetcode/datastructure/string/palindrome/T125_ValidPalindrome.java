package leetcode.datastructure.string.palindrome;

/**
 * @author Kelly
 * @create 2020-04-22 22:05
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 */
public class T125_ValidPalindrome {
    // 双指针
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        s = s.toLowerCase(); // 全部转为小写字母
        int i = 0, j = s.length() - 1;
        while (i < j){
            if (s.charAt(i) < '0' || s.charAt(i) > 'Z' || (s.charAt(i) > '9' && s.charAt(i) < 'a')) {
                i++;
                continue;
            }
            if (s.charAt(j) < '0' || s.charAt(j) > 'Z' || (s.charAt(j) > '9' && s.charAt(j) < 'a')) {
                j--;
                continue;
            }

            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
    public boolean isPalindrome1(String s) {
        if (s == null || s.length() == 0) return true;
        s = s.toLowerCase();
        int i = 0, j = s.length() - 1;
        while (i < j){
            if (!Character.isLetterOrDigit(s.charAt(i))){
                i++;
                continue;
            }
            if (!Character.isLetterOrDigit(s.charAt(j))){
                j--;
                continue;
            }
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}

