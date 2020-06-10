package leetcode.topmianshi;

/**
 * @author Kelly
 * @create 2020-05-27 11:07
 *
 * 找到两个柱子之间的最大容量
 * 双指针, 每次比较两个柱子的高度，较短的决定盛水的容量，更新最大面积
 * 较短柱子的一端移动，因为移动可能获得更大的面积
 */
public class T11_ContainerWithMostWater {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;
        while (left < right){
            int area = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, area);
            if (height[left] < height[right]) left++;
            else right--;
        }
        return maxArea;
    }
}
