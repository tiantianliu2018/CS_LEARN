package leetcode.topmianshi.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kelly
 * @create 2020-05-22 09:52
 * 给定一个整数数组 nums 和一个目标值 target，
 * 请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 */
public class T1_TwoSum {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[0];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int t = target - nums[i];
            if (map.containsKey(t)) return new int[]{map.get(t), i};
            map.put(nums[i], i);
        }
        return new int[0];
    }
}
