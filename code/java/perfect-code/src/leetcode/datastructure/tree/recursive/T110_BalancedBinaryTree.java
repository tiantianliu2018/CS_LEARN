package leetcode.datastructure.tree.recursive;

import leetcode.datastructure.tree.TreeNode;

/**
 * @author Kelly
 * @create 2020-04-22 17:07
 *
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 */
public class T110_BalancedBinaryTree {
    /**
     * 自顶向下
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return false;
        // 计算根节点左右子树的高度差
        int diff = Math.abs(depth(root.left) - depth(root.right));
        // 高度差大于 1，不是平衡二叉树
        if (diff > 1) return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }
    // 计算深度
    public int depth(TreeNode node){
        if (node == null) return 0;
        return Math.max(depth(node.left), depth(node.right)) + 1;
    }

    /**
     * 自底向上
     */
    public boolean isBalanced1(TreeNode root) {
        if (root == null) return true;
        int depth = maxDepth(root);
        return depth != -1;
    }
    public int maxDepth(TreeNode node){
        if (node == null) return 0;
        int left = maxDepth(node.left);
        int right = maxDepth(node.right);
        if (left == -1 || right == -1) return -1; // -1 表示非平衡
        if (Math.abs(left - right) > 1) return -1;  // 非平衡
        return 1 + Math.max(left, right);
    }

}
