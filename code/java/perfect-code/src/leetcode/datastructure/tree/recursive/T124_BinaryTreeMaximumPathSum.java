package leetcode.datastructure.tree.recursive;

import leetcode.datastructure.tree.TreeNode;

/**
 * @author Kelly
 * @create 2020-04-22 20:55
 *
 * 给定一个非空二叉树，返回其最大路径和。
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。
 * 该路径至少包含一个节点，且不一定经过根节点。
 */
public class T124_BinaryTreeMaximumPathSum {
    /**
     * 递归，更新时可以计算最大值，但是返回的时候，只能将左右子树的最大路径返回给上一层节点
     * @param root
     * @return
     */
    int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxSum(root);
        return maxSum;
    }
    // 后序遍历
    private int maxSum(TreeNode node) {
        if (node == null) return 0;
        // 当前节点左子树最大路径和
        int left = Math.max(0, maxSum(node.left));
        // 当前节点右子树最大路径和
        int right = Math.max(0, maxSum(node.right));
        // 更新最大路径
        maxSum = Math.max(maxSum, left + right + node.val);
        // 但是返回上层只能返回左右子树中最大的路径和 + 当前节点的值
        return Math.max(left, right) + node.val;
    }
}
