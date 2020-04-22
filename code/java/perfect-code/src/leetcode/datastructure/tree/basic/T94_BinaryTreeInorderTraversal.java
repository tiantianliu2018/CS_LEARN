package leetcode.datastructure.tree.basic;

import leetcode.datastructure.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Kelly
 * @create 2020-04-21 10:04
 * 二叉树的中序遍历  -- 左子树 -- 根 -- 右子树
 */
public class T94_BinaryTreeInorderTraversal {
    /**
     * 递归解法
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        // 由于 inOrder 里面已经有判断 root 为 null 的逻辑，因此这里不做特判
        inOrder(root, res);
        return res;
    }

    private void inOrder(TreeNode node, List<Integer> res) {
        if (node == null) return;
        inOrder(node.left, res);
        res.add(node.val);
        inOrder(node.right, res);
    }

    /**
     * 迭代解法
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (!stack.isEmpty() || curr != null){
            // 不断往左子树的方向走，模拟递归调用
            while (curr != null){
                stack.push(curr);
                curr = curr.left;
            }
            // 左子树走到头了，从栈中弹出节点并保存，继续看当前节点的右子树
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }

}
