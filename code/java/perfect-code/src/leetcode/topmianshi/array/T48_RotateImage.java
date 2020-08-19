package leetcode.topmianshi.array;

/**
 * @author Kelly
 * @create 2020-06-21 09:37
 *
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * 将图像顺时针旋转 90 度。
 * 原地旋转
 */
public class T48_RotateImage {
    /**
     * 先做转置，然后对称反转每一行
     */
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        int n = matrix.length;
        // 转置矩阵
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // 翻转每一行
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }
    }

    /**
     * 翻转矩形 : 从四个角开始，一次交换四个，由外及内一层一层交换
     */
    public void rotate1(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        int n = matrix.length;
        for (int i = 0; i < (n + 1) / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }
}
