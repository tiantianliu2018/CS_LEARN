package leetcode.datastructure.tree.bfs;

import leetcode.datastructure.tree.TreeNode;

import java.util.*;

/**
 * @author Kelly
 * @create 2020-04-23 21:55
 *
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。
 * 1. 先层次遍历，然后反转结果
 */
public class T107_BinaryTreeLevelOrderTraversalII {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            // 插入时插入到开头
            res.add(0, level);
        }
        return res;
    }
}
