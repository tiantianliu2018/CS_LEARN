package leetcode.topmianshi.array;

/**
 * @author Kelly
 * @create 2020-06-15 08:48
 *
 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
 *
 * 1. 遍历数组构建哈希表，然后从 1 开始 到 len（数组的长度） 遍历寻找，空间不符合要求
 * 2. 将数组当成「哈希表」来用，将 num 放到下标为 num-1 的位置
 *
 */
public class T41_FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 当前数 num，和数组中 num-1 位置的值不相同，交换位置
            while (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]){
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) return i + 1;
        }
        return n + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

