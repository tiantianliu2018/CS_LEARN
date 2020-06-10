package leetcode.topmianshi.array;

/**
 * @author Kelly
 * @create 2020-06-04 11:31
 *
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 */
public class T26_RemoveDuplicatesFromSortedArray {
    /**
     * 设置两个指针 i 和 j，分别初始化为 0 和 1，比较两个位置上的元素是否相同：
     *      相同的话，j++（继续找下面不同的元素）
     *      不同的话，将 nums[j] 放在 nums[i+1] 的位置上
     * 最后返回 i + 1  数组中元素的个数
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int i = 0, j = 1;  // i 记录交换元素的位置
        while (j < nums.length){
            if (nums[j] == nums[i]) {
                j++;
            } else {
                nums[++i] = nums[j];
            }
        }
        return i + 1;
    }
}
