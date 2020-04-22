package leetcode.datastructure.tree.recursive;

import javafx.util.Pair;
import leetcode.datastructure.tree.TreeNode;

import java.util.Stack;

/**
 * @author Kelly
 * @create 2020-04-22 09:53
 *
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，
 * 这条路径上所有节点值相加等于目标和。
 */
public class T112_PathSum {
    /**
     * 递归 -- 前序
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && sum == root.val) return true;
        if (root.left == null && root.right == null && sum != root.val) return false;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    /**
     * 迭代 前序遍历
     */
    public boolean hasPathSum1(TreeNode root, int sum) {
        if (root == null) return false;
        // 保存所遍历的当前节点与当前路径的和
        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
        stack.push(new Pair<>(root, root.val));

        while (!stack.isEmpty()){
            Pair<TreeNode, Integer> top = stack.pop();
            TreeNode curr = top.getKey();
            int currSum = top.getValue();
            // 判断当前路径和 是否等于 target
            if (curr.left == null && curr.right == null && currSum == sum) return true;
            // 右子树入栈，左子树入栈
            if (curr.right != null) stack.push(new Pair<>(curr.right, currSum + curr.right.val));
            if (curr.left != null) stack.push(new Pair<>(curr.left, currSum + curr.left.val));
        }
        return false;

    }

}
