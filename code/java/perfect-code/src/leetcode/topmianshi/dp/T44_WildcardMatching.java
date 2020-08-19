package leetcode.topmianshi.dp;

/**
 * @author Kelly
 * @create 2020-06-20 10:47
 */
public class T44_WildcardMatching {
    public static boolean isMatch(String s, String p) {
        if (s.equals(p)) return true;
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        // base case
        dp[0][0] = true;
        for (int i = 1; i <= p.length(); i++) {
            if (p.charAt(i - 1) == '*') dp[0][i] = dp[0][i - 1];
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') dp[i][j] = dp[i-1][j-1];
                else if (p.charAt(j - 1) == '*'){  // * 可以匹配任意字符串，可以匹配空串或者多个字符
                    dp[i][j] = dp[i][j-1] || dp[i-1][j];  // 空串 || 多个字符的字符串
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public static void main(String[] args) {
        boolean res = isMatch("acdcb", "a*c?b");
        System.out.println(res);
    }
}
