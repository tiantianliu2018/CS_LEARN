package bytedance.array;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Kelly
 * @create 2020-06-28 10:01
 *
 * 给你一个有序整数数组，数组中的数可以是正数、负数、零，请实现一个函数，
 * 这个函数返回一个整数：返回这个数组所有数的平方值中有多少种不同的取值。
 */
public class SortedArrayFindDiff {
    /**
     * HashSet, 通过 HashSet 去重，但是这里没有用到有序这个条件
     */
    public static int findDiff(int[] nums){
        if (nums == null || nums.length == 0) return 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            // 或者是添加绝对值
            set.add(num * num);
        }
        return set.size();
    }

    /**
     * 利用有序这个性质：
     * 平方相等（绝对值相等）：
     *      1. 相邻的两个数相等，要跳过
     *      2. 相反的两个数相等，要跳过
     * 维护两个指针，一个初始为 0，一个初始为数组中最后一个数的位置
     */
    public static int findDiff1(int[] nums){
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int i = 0, j = n - 1;
        int res = 0;
        while (i <= j){  // 看边界
            int num1 = Math.abs(nums[i]);
            int num2 = Math.abs(nums[j]);
            if (num1 > num2) {
                res++;
                i++;
                while (i <= j && Math.abs(nums[i]) == num1) i++;
            } else if (num1 < num2) {
                res++;
                j--;
                while (i <= j && Math.abs(nums[j]) == num2) j--;
            } else {
                res++;
                i++;
                j--;
                while (i <= j && Math.abs(nums[i]) == num1) i++;
                while (i <= j && Math.abs(nums[j]) == num2) j--;
            }
        }
        return res;
    }

    public static int findDiff2(int[] nums){
        if (nums == null || nums.length == 0) return 0;
        int left = 0, right = nums.length - 1;
        int res = 0;
        int pre = nums[0];
        while (left <= right){
            int num1 = Math.abs(nums[left]);
            int num2 = Math.abs(nums[right]);
            // 左边的数更大，因为右边更大的数已经看过了，不会再重复
            if (num1 > num2) {
                if (pre != num1) {
                    res++;
                    pre = num1;
                }
                left++;
            } else {
                if (pre != num2) {
                    res++;
                    pre = num2;
                }
                right--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-5,-3,-1,1,1,2};
        int diff = findDiff(nums);
        int diff1 = findDiff1(nums);
        int diff2 = findDiff2(nums);
        System.out.println(diff);
        System.out.println(diff1);
        System.out.println(diff2);
    }
}
