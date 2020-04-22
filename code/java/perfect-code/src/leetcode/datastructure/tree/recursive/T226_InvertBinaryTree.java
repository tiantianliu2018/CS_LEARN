package leetcode.datastructure.tree.recursive;

import leetcode.datastructure.tree.TreeNode;
import sun.plugin.javascript.navig.Link;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Kelly
 * @create 2020-04-21 21:53
 *
 * 翻转一棵二叉树
 */
public class T226_InvertBinaryTree {
    /**
     * 递归
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        TreeNode tmp = root.right; // 保存右子树
        root.right = invertTree(root.left);
        root.left = invertTree(tmp);
        return root;
    }
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) return root;
        TreeNode right = invertTree1(root.left);
        TreeNode left = invertTree1(root.right);
        root.left = left;
        root.right = right;
        return root;
    }

    /**
     * 迭代写法，BFS
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode curr = queue.poll();
            // 交换当前节点的左右孩子节点
            TreeNode temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;
            // 左右孩子不为空的时候加入队列
            if (curr.left != null) queue.offer(curr.left);
            if (curr.right != null) queue.offer(curr.right);
        }
        return root;
    }
}
