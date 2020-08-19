package bytedance.array;

/**
 * @author Kelly
 * @create 2020-06-27 21:59
 *
 * 给你两个有序整数数组 nums1 和 nums2，
 * 请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 */
public class T88_MergeSortedArray {

    /**
     * 遍历两个数组，较大的元素放在最后面
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int pos = m + n - 1;
        m = m - 1;
        n = n - 1;
        while (m >= 0 && n >= 0){
            if (nums1[m] > nums2[n]) nums1[pos--] = nums1[m--];
            else nums1[pos--] = nums2[n--];
        }
        // 由于将 nums2 的元素放到 nums1，加入是这样的情况
        // nums1 = [4,5,6,0,0,0], nums2 = [1,2,3]
        while (n >= 0) {
            nums1[pos--] = nums2[n--];
        }
    }
}
