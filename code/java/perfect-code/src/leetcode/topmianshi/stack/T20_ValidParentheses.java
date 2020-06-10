package leetcode.topmianshi.stack;

import java.util.Stack;

/**
 * @author Kelly
 * @create 2020-06-02 11:22
 */
public class T20_ValidParentheses {
    /**
     * 栈，遇到左括号入栈，遇到右括号出栈并判断
     */
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) return true;
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') stack.push(c);
            else {
                if (stack.isEmpty()) return false;  // 先遇到右括号
                if (c == ')' && stack.pop() == '(') continue;
                if (c == '}' && stack.pop() == '{') continue;
                if (c == ']' && stack.pop() == '[') continue;
                else return false;
            }
        }
        return stack.isEmpty();
    }
}
