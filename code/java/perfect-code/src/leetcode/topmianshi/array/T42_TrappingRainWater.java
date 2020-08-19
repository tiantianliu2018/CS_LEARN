package leetcode.topmianshi.array;

import java.util.Stack;

/**
 * @author Kelly
 * @create 2020-06-17 08:53
 *
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 */
public class T42_TrappingRainWater {
    /**
     * 单调栈，遍历柱子，当前高度大于栈顶，出栈，计算栈顶位置可以盛的水；
     * 当前高度小于栈顶，入栈，说明该位置要在后面才可能计算到盛水的多少
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        if (height == null || height.length < 3) return 0;
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int pos = stack.pop();  // 要计算能接雨水的位置
                if (stack.isEmpty()) break;
                int width = i - stack.peek() - 1;
                // 哪根柱子决定接水的多少
                int currHeight = Math.min(height[i], height[stack.peek()]) - height[pos];
                res += width * currHeight;
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        int res = trap(nums);
        System.out.println(res);
    }
}
