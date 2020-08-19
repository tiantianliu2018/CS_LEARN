package leetcode.topmianshi.string;

/**
 * @author Kelly
 * @create 2020-06-14 10:38
 *
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述
 */
public class T38_CountAndSay {
    /**
     * 迭代
     * @param n
     * @return
     */
    public String countAndSay1(int n) {
        String str = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            char pre = str.charAt(0);
            int count = 1;
            for (int j = 1; j < str.length(); j++) {
                char num = str.charAt(j);
                if (num == pre) count++;  // 重复数字，看 count 有几个
                else {
                    sb.append(count).append(pre);
                    pre = num;
                    count = 1;
                }
            }
            sb.append(count).append(pre);
            str = sb.toString();
        }
        return str;
    }

    /**
     * 递归
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        if (n == 1) return "1";
        String s = countAndSay(n - 1);
        StringBuilder sb = new StringBuilder();
        int pre = 0;
        int i = 1;
        for (i = 1; i < s.length(); i++) {
            if (s.charAt(pre) != s.charAt(i)){
                int count = i - pre;
                sb.append(count).append(s.charAt(pre));
                pre = i;
            }
        }
        if (pre != i){
            int count = i - pre;
            sb.append(count).append(s.charAt(pre));
        }
        return sb.toString();
    }
}
