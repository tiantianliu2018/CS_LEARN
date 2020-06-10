package leetcode.topmianshi.traceback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kelly
 * @create 2020-06-02 10:46
 *
 * 回溯
 */
public class T17_LetterCombinationsOfAPhoneNumber {
    String[] letters = new String[]{"0", "1","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        backtracing(res, digits, new StringBuilder());
        return res;
    }

    private void backtracing(List<String> res, String digits, StringBuilder tmp) {
        if (tmp.length() == digits.length()){
            res.add(tmp.toString());
            return;
        }
        int index = digits.charAt(tmp.length()) - '0';
        String letter = letters[index];
        for (char c : letter.toCharArray()) {
            tmp.append(c);
            backtracing(res, digits, tmp);
            tmp.deleteCharAt(tmp.length() - 1);
        }
    }
}
