package bytedance.array;

/**
 * @author Kelly
 * @create 2020-06-28 21:46
 */
public class FindDiffNumInBitonicArray {
    public int findDiffNums(int[] nums){
        if (nums == null || nums.length == 0) return 0;
        int left = 0, right = nums.length - 1;
        int res = 0;
        while (left <= right){
            if (nums[left] == nums[right]){
                res++;
                int temp = nums[left];
                while (left <= right && nums[left] == temp) left++;
                while (left <= right && nums[right] == temp) right--;
            } else if (nums[left] < nums[right]){
                res++;
                int temp = nums[left];
                while (left <= right && nums[left] == temp) left++;
            } else {
                res++;
                int temp = nums[right];
                while (left <= right && nums[right] == temp) right--;
            }
        }
        return res;
    }
}