package leetcode.datastructure.tree.recursive;

import leetcode.datastructure.tree.TreeNode;

/**
 * @author Kelly
 * @create 2020-04-22 21:23
 *
 * 给定一棵二叉树，你需要计算它的直径长度。
 * 一棵二叉树的直径长度是任意两个结点路径长度中的最大值。
 * 这条路径可能穿过也可能不穿过根结点。
 */
public class T543_DiameterOfBinaryTree {
    int diameter = Integer.MIN_VALUE; // 直径长度

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        getDiameter(root);
        return diameter;

    }
    // 获得两个节点之间的直径，后序遍历
    private int getDiameter(TreeNode node) {
        if (node == null) return 0;
        int left = getDiameter(node.left); // 左子树的直径
        int right = getDiameter(node.right); // 右子树直径
        diameter = Math.max(diameter, left + right);
        return Math.max(left, right) + 1;
    }
}
