package leetcode.topmianshi.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Kelly
 * @create 2020-05-28 09:53
 *
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
 * 请你找出所有满足条件且不重复的三元组。
 */
public class T15_3Sum {
    /**
     * 对数组排序，固定最左边的一个数，转变为求两个数的和
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) return res;
        Arrays.sort(nums);
        int n = nums.length;

        if (nums[0] > 0 || nums[n - 1] < 0) return res;  // 最小的数就是正数 或者最大值是负数，不可能出现三个数的和为 0

        for (int i = 0; i < nums.length - 2; i++){  // 固定最左边的数
            if (nums[i] > 0) break;  // 最左边的数为正数，无解，提前退出
            if (i > 0 && nums[i] == nums[i - 1]) continue;  // 跳过最左边重复的数

            int target = - nums[i];  // 剩下的两个数的和

            // 双指针，找后面两个数的和
            int left = i + 1, right = n - 1;
            while (left < right){
                if (nums[left] + nums[right] == target){
                    res.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));
                    // 跳过重复值
                    while (left < right && nums[left + 1] == nums[left]) left++;
                    left++;
                    while (right > left && nums[right - 1] == nums[right]) right--;
                    right--;
                } else if (nums[left] + nums[right] < target){
                    left++;
                    // 跳过重复值
                    while (left < right && nums[left] == nums[left - 1]) left++;
                } else {
                    right--;
                    // 跳过重复值
                    while (right > left && nums[right] == nums[right + 1]) right--;
                }
            }
        }
        return res;
    }
}
