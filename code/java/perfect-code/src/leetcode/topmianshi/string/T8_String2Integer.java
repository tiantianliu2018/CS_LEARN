package leetcode.topmianshi.string;

/**
 * @author Kelly
 * @create 2020-05-25 11:00
 */
public class T8_String2Integer {
    public static void main(String[] args) {
        int res = myAtoi("42");
        System.out.println(res);
    }
    public static int myAtoi(String str) {
        String s = str.trim();  // 去掉首尾空格
        if (s.length() == 0) return 0;
        boolean flag = true;  // 标记正负数，默认为正数
        // 判断第一个位置的字符是否有效（+，- 或者数字）
        if (s.charAt(0) == '+' || s.charAt(0) == '-' || (s.charAt(0) >= '0' && s.charAt(0) <= '9')){
            long res = 0;   // 保存结果
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (i == 0 && c == '-') flag = false;  // 第一个字符，特别判断
                else if (i == 0 && c == '+') flag = true;
                else if (c >= '0' && c <= '9'){
                    res = res * 10 + c - '0';
                    if (flag && res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
                    else if (!flag && -res < Integer.MIN_VALUE) return Integer.MIN_VALUE;
                } else break;  // 中间非数字，直接跳出
            }
            if (flag) return (int) res;
            else return -(int) res;
        } else return 0;
    }
}
