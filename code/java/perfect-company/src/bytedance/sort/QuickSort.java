package bytedance.sort;

/**
 * @author Kelly
 * @create 2020-06-28 21:56
 */
public class QuickSort {
    public void quickSort(int[] nums, int left, int right){
        int pivot = nums[right];
        int pivotIndex = right;
        while (left < right){
            while (left < right && nums[left] <= pivot) left++;
            while (left < right && nums[right] >= pivot) right--;

        }

    }
}
