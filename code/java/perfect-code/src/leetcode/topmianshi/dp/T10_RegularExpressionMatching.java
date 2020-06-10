package leetcode.topmianshi.dp;

/**
 * @author Kelly
 * @create 2020-05-26 11:40
 *
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个「前面」的那一个元素
 */
public class T10_RegularExpressionMatching {
    /**
     * 动态规划，dp[i,j] 表示 s 的前 i 个字符与 p 的前 j 个字符的匹配情况
     * 如果当前字符匹配或者 ‘.’ dp[i][j] = dp[i-1][j-1]
     * 如果当前字符串是‘*’，有两种情况，看 * 前面的字符 p[j-1] 是否和 s[i] 匹配
     *      如果不匹配，说明 * 和之前的字符都可以忽略 dp[i][j] = dp[i][j-2]
     *      如果匹配或者 p[j-1] = '.', 说明 * 和之前的字符可以匹配多个(包括 0 个)
     *          若匹配 0 个 dp[i][j] = dp[i][j-2]
     *          若匹配多个 dp[i][j] = dp[i-1][j]
     */
    public boolean isMatch(String s, String p) {
        if (s != null && p == null) return false;
        if (s.equals(p)) return true;
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= p.length(); i++) {
            dp[0][i]  = p.charAt(i - 1) == '*' && dp[0][i - 2];
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.'){
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*'){
                    dp[i][j] = dp[i][j - 2];
                    // 当前 s[i] 匹配 * 前面的额那个字符
                    if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.'){
                        dp[i][j] |= dp[i - 1][j];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
