package leetcode.datastructure.tree.basic;

import leetcode.datastructure.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Kelly
 * @create 2020-04-21 09:48
 * 二叉树的前序遍历  根 -- 左子树 -- 右子树
 */
public class T144_BinaryTreePreorderTraversal {
    /**
     * 递归解法
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preOrder(root, res);
        return res;
    }

    private void preOrder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        preOrder(root.left, res);
        preOrder(root.right, res);
    }

    /**
     * 迭代解法
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;  // 特判空节点
        Stack<TreeNode> stack = new Stack<>(); // 存节点的栈
        stack.add(root);
        while (!stack.isEmpty()){
            TreeNode top = stack.pop();
            res.add(top.val);
            if (top.right != null) stack.push(top.right);
            if (top.left != null) stack.push(top.left);
        }
        return res;
    }

}
