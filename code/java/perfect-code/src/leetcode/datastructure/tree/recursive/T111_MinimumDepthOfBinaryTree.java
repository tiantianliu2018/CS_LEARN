package leetcode.datastructure.tree.recursive;

import javafx.util.Pair;
import leetcode.datastructure.tree.TreeNode;

import java.util.Stack;

/**
 * @author Kelly
 * @create 2020-04-22 16:49
 *
 * 给定一个二叉树，找出其最小深度。
 */
public class T111_MinimumDepthOfBinaryTree {
    /**
     * 递归方法
     * 要单独判断左子树或者右子树只有一个为空的情况，因为最小深度定义为根节点到叶子节点的路径
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right != null) return 1 + minDepth(root.right);
        if (root.left != null && root.right == null) return 1 + minDepth(root.left);
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    /**
     * 迭代方法，前序遍历，用辅助栈保存节点和深度
     */
    public int minDepth1(TreeNode root) {
        if (root == null) return 0;
        // 保存遍历节点与深度的辅助栈
        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
        stack.push(new Pair<>(root,1));
        int minDepth = Integer.MAX_VALUE;
        while (!stack.isEmpty()){
            Pair<TreeNode, Integer> top = stack.pop();
            TreeNode node = top.getKey();
            Integer depth = top.getValue();
            // 到叶子节点才更新最小值
            if (node.left == null && node.right == null) minDepth = Math.min(minDepth, depth);

            if (node.right != null) stack.push(new Pair<>(node.right, depth + 1));
            if (node.left != null) stack.push(new Pair<>(node.left, depth + 1));
        }
        return minDepth;
    }
}
