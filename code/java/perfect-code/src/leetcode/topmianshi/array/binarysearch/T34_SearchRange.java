package leetcode.topmianshi.array.binarysearch;

/**
 * @author Kelly
 * @create 2020-06-13 10:18
 *
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 */
public class T34_SearchRange {
    /**
     * 搜索左边界，左边界的左边的数严格小于左边界
     * 搜索右边界，右边界的右边的数严格大于右边界
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1,-1};
        int[] res = new int[2];
        res[0] = searchLeft(nums, target);
        res[1] = searchRight(nums, target);
        return res;
    }
    public int searchLeft(int[] nums, int target){
        int left = 0, right = nums.length - 1;
        while (left < right){
            int mid = left + (right - left) / 2;
            if (nums[mid] < target){
                left = mid + 1;  // [l, mid] 肯定不是解
            } else {            // 相等的时候，有可能是解，所以搜索到 mid
                right = mid;
            }
        }
        // 没有搜索到左边界，直到 left = num.length
        if (nums[left] == target) return left;
        return -1;
    }
    public int searchRight(int[] nums, int target){
        int left = 0, right = nums.length - 1;
        while (left < right){
            int mid = left + (right - left + 1) / 2;
            if (nums[mid] > target){   // [mid, r] 肯定不是解
                right = mid - 1;    //  下次所搜 [left, mid-1]
            } else {
                left = mid;   // 前面 mid 的计算可能使得 mid = left ，那么这里会变成死循环
            }
        }
        if (nums[left] == target) return left;  // left == right
        return -1;
    }
}
