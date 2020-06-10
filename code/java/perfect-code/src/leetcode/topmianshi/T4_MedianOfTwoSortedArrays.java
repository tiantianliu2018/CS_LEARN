package leetcode.topmianshi;

import java.util.Map;

/**
 * @author Kelly
 * @create 2020-05-22 10:25
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 *  1. 暴力的话，先将两个数组合并到一个数组，整体排序，最后返回中位数
 */
public class T4_MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        double res = findMedianSortedArrays(new int[]{}, new int[]{1});
        System.out.println(res);
    }
    // 双指针归并，返回中位数
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int i = 0, j = 0, idx = 0;
        int[] nums = new int[m + n];
        while (i < m && j < n){
            if (nums1[i] < nums2[j]) nums[idx++] = nums1[i++];
            else nums[idx++] = nums2[j++];
        }
        while (i < m) nums[idx++] = nums1[i++];
        while (j < n) nums[idx++] = nums2[j++];

        if ((m + n) % 2 != 0) return nums[(m + n) / 2];
        return (nums[(m + n) / 2] + nums[(m + n) / 2 - 1]) / 2.0;
    }

    // 二分查找
    public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        // 交换，每次操作最短的序列
        if (nums1.length > nums2.length){
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        int m = nums1.length, n = nums2.length;
        int left = 0, right = m;
        int leftTotal = (m + n + 1) / 2;
        while (left <= right){
            int i = left + (right - left) / 2;
            int j = leftTotal - i;
            int num1LeftMax = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
            int num2LeftMax = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];

            int num1RightMin = i == m ? Integer.MAX_VALUE : nums1[i];
            int num2RightMin = j == n ? Integer.MAX_VALUE : nums2[j];

            if (num1LeftMax <= num2RightMin && num2LeftMax <= num1RightMin){  // 找到了合适的分界线
                if ((m + n) % 2 != 0) return Math.max(num1LeftMax, num2LeftMax);  // 返回左边最大的
                return (Math.max(num1LeftMax, num2LeftMax) + Math.min(num1RightMin, num2RightMin)) / 2.0;
            } else if (num1LeftMax > num2RightMin){  // 左边分的太多了，往左收缩
                right = i - 1;
            } else {
                left = i + 1;
            }
        }
        return 0.0;
    }
}
