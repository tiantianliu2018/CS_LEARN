package bytedance;

/**
 * @author Kelly
 * @create 2020-06-27 13:41
 *
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 */
public class T55_JumpGame {
    /**
     * 贪心，遍历每一个位置，看可以到达的最远距离
     */
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return true;
        int n = nums.length;
        // 可以跳跃到的最大距离
        int maxDist = 0;
        for (int i = 0; i < n; i++) {
            if (maxDist < i) return false;
            if (maxDist >= n - 1) return true;
            maxDist = Math.max(maxDist, i + nums[i]);
        }
        return true;
    }

    public boolean canJump1(int[] nums) {
        if (nums == null || nums.length == 0) return true;
        int pos = nums.length - 1;
        // 从倒数第二个位置开始看起
        for (int i = nums.length - 2; i >= 0 ; i--) {
            if (i + nums[i] >= pos) pos = i;
            else return false;
        }
        return pos == 0;
    }

}
