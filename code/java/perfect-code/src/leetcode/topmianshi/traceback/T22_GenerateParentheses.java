package leetcode.topmianshi.traceback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kelly
 * @create 2020-06-03 11:12
 *
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 */
public class T22_GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n <= 0) return res;
        dfs(res, n, n, "");
        return res;
    }

    /**
     * @param left 左括号还有几个没用
     * @param right 右括号还有几个没用
     * @param curr
     */
    private void dfs(List<String> res, int left, int right, String curr) {
        if (left == 0 && right == 0) {
            res.add(curr);
            return;
        }
        if (left > right) return;
        if (left > 0) {
            dfs(res, left - 1, right, curr + "(");
        }
        if (right > 0){
            dfs(res, left, right - 1, curr + ")");
        }
    }

}
