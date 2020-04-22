package leetcode.datastructure.tree.recursive;

import leetcode.datastructure.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Kelly
 * @create 2020-04-21 20:55
 *
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 *
 */
public class T100_SameTree {
    /**
     * 递归方法
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        // 两个二叉树相同的条件（根节点值相同，左右子树也一样）
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 迭代方法，用两个队列，逐一比较两棵树的每一个节点的值
     */
    public static boolean isSameTree1(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        // 建立两个队列 BFS 层序遍历
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.add(p);
        queue2.add(q);
        while (!queue1.isEmpty() || !queue2.isEmpty()){
            TreeNode top1 = queue1.poll();
            TreeNode top2 = queue2.poll();
            if (top1 == null && top2 == null) continue;
            if (top1 == null || top2 == null) return false;
            if (top1.val != top2.val) return false;
            queue1.add(top1.left);
            queue1.add(top1.right);
            queue2.add(top2.left);
            queue2.add(top2.right);
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);

        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(2);

        node1.left = node2;
        node3.right = node4;

        boolean res = isSameTree1(node1, node3);
        System.out.println(res);
    }
}
