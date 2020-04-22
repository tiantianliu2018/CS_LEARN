package leetcode.datastructure.tree.recursive;

import javafx.util.Pair;
import leetcode.datastructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Kelly
 * @create 2020-04-21 22:15
 *
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 */
public class T257_BinaryTreePaths {
    /**
     * 递归 深度优先遍历（前序遍历）
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        dfs(root, res, "");
        return res;
    }

    private void dfs(TreeNode node, List<String> res, String curr) {
        // 当前节点是叶子节点，将叶子节点的值加入当前路径，并找到了一条路径
        if (node.left == null && node.right == null) {
            res.add(curr + node.val);
            return;
        }
        // 若不是叶子节点，访问当前节点，并递归遍历其左子树和右子树
        curr += node.val + "->";
        if (node.left != null) dfs(node.left, res, curr);
        if (node.right != null) dfs(node.right, res, curr);
    }

    /**
     * 迭代方法的深度优先搜索  前序遍历
     */
    public static List<String> binaryTreePaths1(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;

        Stack<Pair<TreeNode, String>> stack = new Stack<>(); // 保存节点和路径
        stack.push(new Pair<>(root, ""));
        while (!stack.isEmpty()){
            Pair<TreeNode, String> top = stack.pop();
            // 遍历到的当前节点
            TreeNode curr = top.getKey();
            String tmp = top.getValue();
            tmp += curr.val;
            // 若当前节点是叶子节点
            if (curr.left == null && curr.right == null) res.add(tmp);
            tmp += "->";
            if (curr.right != null) stack.push(new Pair<>(curr.right, tmp));
            if (curr.left != null) stack.push(new Pair<>(curr.left, tmp));
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.right = node5;
        List<String> res = binaryTreePaths1(node1);
        res.forEach(System.out::println);

    }
}
