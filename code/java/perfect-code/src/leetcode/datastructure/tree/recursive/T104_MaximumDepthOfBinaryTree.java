package leetcode.datastructure.tree.recursive;

import javafx.util.Pair;
import leetcode.datastructure.tree.TreeNode;
import java.util.Stack;

/**
 * @author Kelly
 * @create 2020-04-21 20:18
 *
 * 给定一个二叉树，找出其最大深度。
 */
public class T104_MaximumDepthOfBinaryTree {
    /**
     * 递归方法
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max((maxDepth(root.left)), maxDepth(root.right)) + 1;
    }

    /**
     * 迭代方法
     */
    public int maxDepth1(TreeNode root) {
        if (root == null) return 0;
        // 存储当前节点与当前节点深度的栈
        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
        stack.push(new Pair<>(root, 1));

        int maxDepth = 0;
        while (!stack.isEmpty()){
            Pair<TreeNode, Integer> top = stack.pop();
            TreeNode curr = top.getKey(); // 当前节点
            int currDepth = top.getValue();  // 当前节点所在的深度

            // 更新最大深度
            maxDepth = Math.max(maxDepth, currDepth);
            // 孩子节点入栈，深度加 1
            if (curr.left != null) stack.push(new Pair<>(curr.left, currDepth + 1));
            if (curr.right != null) stack.push(new Pair<>(curr.right, currDepth + 1));
        }
        return maxDepth;
    }
}
