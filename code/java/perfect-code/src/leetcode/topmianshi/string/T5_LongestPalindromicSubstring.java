package leetcode.topmianshi.string;

/**
 * @author Kelly
 * @create 2020-05-22 10:29
 *
 * 给定一个字符串 s，找到 s 中最长的回文子串。
 */
public class T5_LongestPalindromicSubstring {
    // 中心扩散法
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        int maxLen = 1;
        String res = s.substring(0, 1);
        // 中心扩散找回文串
        for (int i = 0; i < s.length(); i++) {
            String s1 = centerSpread(s, i, i);// 奇数，同一个回文中心
            String s2 = centerSpread(s, i, i + 1);// 偶数
            String currMaxLenStr = s1.length() > s2.length() ? s1 : s2;
            if (currMaxLenStr.length() > maxLen){
                maxLen = currMaxLenStr.length();
                res = currMaxLenStr;
            }
        }
        return res;
    }
    public String centerSpread(String s, int left, int right){
        int i = left, j = right;
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)){
            i--;
            j++;
        }

        return s.substring(i + 1, j);
    }
    /**
     * 动态规划：dp[i][j] 表示子串 s[i][j] 是否是回文串
     * base case: dp[i][i] = true  单个字母一定是回文串
     * 状态转移：dp[i][j] = dp[i+1][j-1] dp[i+1][j-1] = true && s[i] == s[j]
     */
    public String longestPalindrome1(String s) {
        if (s == null || s.length() == 0) return "";
        int n = s.length();
        boolean[][] dp = new boolean[n + 1][n + 1];
        // base case
        for (int i = 0; i < n; i++) dp[i][i] = true;
        int maxLen = 1, startIdx = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) == s.charAt(j) && (dp[i+1][j-1] || j - i < 3)){
                    dp[i][j] = true;
                    int currLen = j - i + 1;
                    if (currLen > maxLen){
                        maxLen = currLen;
                        startIdx = i;
                    }
                }
            }
        }
        return s.substring(startIdx, startIdx + maxLen);
    }
}
