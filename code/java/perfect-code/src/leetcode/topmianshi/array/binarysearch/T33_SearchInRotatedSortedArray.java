package leetcode.topmianshi.array;

/**
 * @author Kelly
 * @create 2020-06-13 09:47
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 */
public class T33_SearchInRotatedSortedArray {
    /**
     * 二分搜索
     * 排序数组翻转后只能保证部分有序，因此只能在有序的区间中再执行二分搜索
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < nums[right]){  // 右半边部分有序
                if (nums[mid] < target && target <= nums[right]){  // 答案就在这段区间里面
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {  // 左半边部分有序
                if (nums[left] <= target && target < nums[mid]){  // 答案在这段区间
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
}
