package leetcode.topmianshi.string;

/**
 * @author Kelly
 * @create 2020-06-07 11:25
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，
 * 在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。
 * 如果不存在，则返回  -1
 */
public class T28_ImplementStrStr {
    /**
     * 暴力解法
     */
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) return 0;
        int i = 0, j = 0;
        int start = 0;
        while (i < haystack.length() && j < needle.length()){
            if (haystack.charAt(i) == needle.charAt(j)){
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == needle.length()) return i - j;
        return -1;
    }

    /**
     * KMP 算法
     */
    public int strStr1(String haystack, String needle) {
        if (needle == null || needle.length() == 0) return 0;
        int i = 0, j = 0;
        int[] next = getNext(needle);
        while (i < haystack.length() && j < needle.length()){
            if (haystack.charAt(i) == needle.charAt(j)){
                i++;
                j++;
            } else if (next[j] == -1){
                i++;
            } else {
                j = next[j];
            }
        }
        return j == needle.length() ? i - j : -1;
    }

    private int[] getNext(String needle) {
        char[] chars = needle.toCharArray();
        if (chars.length == 1) return new int[]{-1};
        int[] next = new int[chars.length];
        next[0] = -1;
        next[1] = 0;
        int p = 0, idx = 2;
        while (idx < chars.length){
            if (chars[idx - 1] == chars[p]){
                next[idx++] = ++p;
            } else if (p > 0){
                p = next[p];
            } else {
                next[idx++] = 0;
            }
        }
        return next;

    }
}
