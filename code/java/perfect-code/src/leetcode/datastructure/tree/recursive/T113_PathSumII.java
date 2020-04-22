package leetcode.datastructure.tree.recursive;

import leetcode.datastructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kelly
 * @create 2020-04-22 10:24
 */
public class T113_PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        dfs(root, sum, res, new ArrayList<Integer>());
        return res;
    }

    /**
     * DFS + 回溯
     */
    private void dfs(TreeNode node, int sum, List<List<Integer>> res, ArrayList<Integer> curr) {
        if (node == null) return;
        // 将当前节点加入路径
        curr.add(node.val);
        // 叶子节点，加入结果，若在这里进行 return, 需要在 return 前移除 叶子节点
        if (node.left == null && node.right == null && sum == node.val) {
            res.add(new ArrayList<>(curr));
        }
        // 非叶子结点
        if (node.left != null) dfs(node.left, sum - node.val, res, curr);
        if (node.right != null) dfs(node.right, sum - node.val, res, curr);
        curr.remove(curr.size() - 1);  // 回退上一个节点，回溯
    }
}
