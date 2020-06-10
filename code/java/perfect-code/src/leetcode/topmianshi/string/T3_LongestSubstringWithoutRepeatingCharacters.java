package leetcode.topmianshi.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kelly
 * @create 2020-05-22 10:09
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class T3_LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        int res = lengthOfLongestSubstring("tmmzuxt");
        System.out.println(res);
    }
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        int left = 0;
        int maxLen = 1;
        Map<Character, Integer> charMap = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (charMap.containsKey(c)){
                left = Math.max(left, charMap.get(c) + 1) ;  // 直接更新到没有重复字符的位置
            }
            maxLen = Math.max(maxLen, right - left + 1);
            charMap.put(c, right);
        }
        return maxLen;
    }
}
