package leetcode.datastructure.array;

/**
 * @author Kelly
 * @create 2020-04-23 16:43
 */
public class T26_RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int i = 0, j = 1;
        while (j < nums.length){
            if (nums[j] == nums[j-1]) j++;
            else {
                nums[++i] = nums[j];
                j++;
            }
        }
        return i+1;
    }
}
