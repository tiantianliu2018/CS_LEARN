package leetcode.datastructure.tree.recursive;

import javafx.util.Pair;
import leetcode.datastructure.tree.TreeNode;

import java.util.Stack;

/**
 * @author Kelly
 * @create 2020-04-22 16:08
 */
public class T129_SumRootToLeafNumbers {
    /**
     * 递归方法
     */
    int sum = 0;
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        dfs(root, 0);
        return sum;
    }

    private void dfs(TreeNode node, int curr) {
        if (node == null) return;
        curr += node.val;
        // 叶子节点，将该值加进来
        if (node.left == null && node.right == null){
            sum += curr;
        }
        if (node.left != null) dfs(node.left, curr * 10);
        if (node.right != null) dfs(node.right, curr * 10);
    }

    /**
     * 迭代方法
     */
    public int sumNumbers1(TreeNode root) {
        if (root == null) return 0;
        // 保存当前节点和到当前节点路径表示的数
        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
        stack.add(new Pair<>(root, 0));
        int sum= 0;
        while (!stack.isEmpty()){
            Pair<TreeNode, Integer> top = stack.pop();
            TreeNode node = top.getKey();
            int value = top.getValue();

            value *= 10;
            value += node.val;
            // 叶子节点加到最终结果里
            if (node.left == null && node.right == null) sum += value;

            if (node.right != null) stack.add(new Pair<>(node.right, value));
            if (node.left != null) stack.add(new Pair<>(node.left, value));
        }
        return sum;
    }
}
