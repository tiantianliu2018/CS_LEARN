package leetcode.datastructure.tree.basic;

import leetcode.datastructure.tree.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Kelly
 * @create 2020-04-21 11:53
 *
 * 二叉树的层序遍历
 */
public class T102_BinaryTreeLevelOrderTraversal {
    /**
     * 二叉树的层序遍历，通过队列的实现
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>(); // 辅助队列
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size(); // 记录每一层节点的个数
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                level.add(curr.val);
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
            res.add(level);
        }
        return res;
    }
}
