package leetcode.topmianshi.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kelly
 * @create 2020-06-21 09:28
 *
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 */
public class T46_Permutations {
    /**
     * 回溯法，用一个 visited 数组记录数字是否访问过
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        boolean[] visited = new boolean[nums.length];
        backtracking(res, nums, new ArrayList<Integer>(), visited);
        return res;
    }

    private void backtracking(List<List<Integer>> res, int[] nums, ArrayList<Integer> curr, boolean[] visited) {
        if (curr.size() == nums.length) {
            res.add(curr);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            curr.add(nums[i]);
            backtracking(res, nums, curr, visited);
            curr.remove(curr.size() - 1);
            visited[i] = false;
        }
    }
}
