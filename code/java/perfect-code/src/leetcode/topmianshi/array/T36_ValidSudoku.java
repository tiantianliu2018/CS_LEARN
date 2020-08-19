package leetcode.topmianshi.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kelly
 * @create 2020-06-13 11:14
 */
public class T36_ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) return false;
        Map<Character, Integer>[] rowMaps = new HashMap[9];  // 9 个 hashmap
        Map<Character, Integer>[] colMaps = new HashMap[9];
        Map<Character, Integer>[] boxMaps = new HashMap[9];
        // Map 数组初始化
        for (int i = 0; i < 9; i++) {
            rowMaps[i] = new HashMap<>();
            colMaps[i] = new HashMap<>();
            boxMaps[i] = new HashMap<>();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') continue;
                int boxIdx = (i / 3) * 3 + j / 3;
                rowMaps[i].put(c,rowMaps[i].getOrDefault(c, 0) + 1);
                colMaps[j].put(c, colMaps[j].getOrDefault(c, 0) + 1);
                boxMaps[boxIdx].put(c, boxMaps[boxIdx].getOrDefault(c, 0) + 1);

                if (rowMaps[i].get(c) > 1 || colMaps[j].get(c) > 1 || boxMaps[boxIdx].get(c) > 1) return false;
            }
        }
        return true;
    }
}
