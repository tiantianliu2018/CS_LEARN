package leetcode.datastructure.tree.recursive;

import leetcode.datastructure.tree.TreeNode;
import sun.plugin.javascript.navig.Link;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Kelly
 * @create 2020-04-21 21:33
 *
 * 给定一个二叉树，检查它是否是镜像对称的。
 */
public class T101_SymmetricTree {
    /**
     * 递归写法，镜像对称，一个节点的左子树（右子树）与另一个节点的右子树（左子树）相同，同时这两个节点的值也相同
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSame(root.left, root.right);
    }
    public boolean isSame(TreeNode node1, TreeNode node2){
        if (node1 == null && node2 == null) return true;
        if (node1 == null || node2 == null) return false;
        if (node1.val != node2.val) return false;
        return isSame(node1.left, node2.right) && isSame(node1.right, node2.left);
    }

    /**
     * 迭代解法：层次遍历
     */
    public boolean isSymmetric1(TreeNode root) {
        if (root == null) return true;
        if (root.left == null && root.right == null) return true;
        if (root.left == null || root.right == null) return false;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()){
            TreeNode first = queue.poll();
            TreeNode second = queue.poll();
            if (first == null && second == null) continue;
            if (first == null || second == null) return false;
            if (first.val != second.val) return false;
            // 第一个节点的左子树应该和第二个节点的右子树相同
            queue.add(first.left);
            queue.add(second.right);
            queue.add(first.right);
            queue.add(second.left);
        }
        return true;
    }
}
