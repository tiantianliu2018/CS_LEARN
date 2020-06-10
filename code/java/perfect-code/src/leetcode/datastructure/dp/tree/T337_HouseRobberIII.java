package leetcode.datastructure.dp.tree;

import leetcode.datastructure.tree.TreeNode;

/**
 * @author Kelly
 * @create 2020-04-23 21:32
 */
public class T337_HouseRobberIII {
    public int rob(TreeNode root) {
        if (root == null) return 0;
        int[] res = robHelper(root);
        return Math.max(res[0], res[1]);
    }

    /**
     * 用一个数组保存抢或不抢，解法已经比较好了
     * @param node
     * @return
     */
    public int[] robHelper(TreeNode node){
        int[] res = new int[2];
        if (node == null) return res;

        int[] left = robHelper(node.left);
        int[] right = robHelper(node.right);

        // 当前节点抢，则孩子节点不抢
        res[0] = left[1] + right[1] + node.val;
        // 当前节点不抢，孩子节点可抢可不抢
        res[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return res;
    }
}
