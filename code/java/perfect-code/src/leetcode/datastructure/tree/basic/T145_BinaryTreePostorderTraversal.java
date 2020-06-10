package leetcode.datastructure.tree.basic;

import leetcode.datastructure.tree.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author Kelly
 * @create 2020-04-21 10:36
 * 二叉树的后序遍历  左子树 -- 右子树 -- 根
 */
public class T145_BinaryTreePostorderTraversal {
    /**
     * 递归解法
     */
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postOrder(root, res);
        return res;
    }

    private void postOrder(TreeNode node, List<Integer> res) {
        if (node == null) return;
        postOrder(node.left, res);
        postOrder(node.right, res);
        res.add(node.val);
    }

    /**
     * 迭代解法：前序遍历的转化
     * 前序遍历是：根左右 --> 先将左子树入栈，可以转换为 『 根 - 右 - 左 』
     * 再将最终结果逆序，变为『 左-右-根 』
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode top = stack.pop();
            if (top.left != null) stack.push(top.left);
            if (top.right != null) stack.push(top.right);
            res.add(top.val);
        }
        Collections.reverse(res);
        
        return res;
    }

    /**
     * 迭代解法：双栈实现
     * s1 节点访问顺序为 - 根-左-右（其实就是前序遍历的改进）
     * s2 用来逆序辅助
     */
    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(root);
        // 这一部分相当于前序遍历的改进
        while (!s1.isEmpty()){
            TreeNode curr = s1.pop();  // 每棵子树的根节点
            s2.push(curr);  // s2 栈用来逆序
            if (curr.left != null) s1.push(curr.left);
            if (curr.right != null) s1.push(curr.right);
        }
        while (!s2.isEmpty()){
            res.add(s2.pop().val);
        }
        return res;
    }

    /**
     * 迭代解法，单栈实现
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal4(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        TreeNode curr;  // 当前栈顶
        TreeNode last = root; // 上一次访问的节点(最近弹出并打印)
        while (!stack.isEmpty()){
            curr = stack.peek();  // 栈顶元素
            // 左孩子不为空，并且左右孩子上次都没有访问，左孩子入栈
            // 假设左右孩子有一个被访问过了，说明现在是自底向上返回，无需再重新入栈处理
            if (curr.left != null && last != curr.left && last != curr.right){
                stack.push(curr.left);
            } else if (curr.right != null && last != curr.right){  // 右孩子没有处理过
                stack.push(curr.right);
            } else {  // 左右孩子都已经打印了
                res.add(stack.pop().val);  // 弹出当前节点
                last = curr;
            }
        }
        return res;
    }
}
