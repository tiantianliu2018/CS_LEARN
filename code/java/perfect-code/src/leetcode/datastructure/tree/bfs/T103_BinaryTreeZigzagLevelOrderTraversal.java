package leetcode.datastructure.tree.bfs;

import leetcode.datastructure.tree.TreeNode;

import java.util.*;

/**
 * @author Kelly
 * @create 2020-04-24 10:50
 *
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。
 */
public class T103_BinaryTreeZigzagLevelOrderTraversal {
    /**
     * 锯齿形遍历，仍然是层次遍历，但是需要设置一个特殊标记，标记是奇数层还是偶数层（或者标记需不需要反转）
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean isReverse = false;
        while (!queue.isEmpty()){
            int size = queue.size(); // 每一层节点对额个数
            List<Integer> level = new LinkedList<>();
            // 遍历每一层的节点
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                level.add(curr.val);
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            if (isReverse){  // 此次需要反转
                Collections.reverse(level);
                isReverse = false;
            } else {  // 此次不需要反转，下次就要进行翻转
                isReverse = true;
            }
            res.add(level);
        }
        return res;
    }
}
