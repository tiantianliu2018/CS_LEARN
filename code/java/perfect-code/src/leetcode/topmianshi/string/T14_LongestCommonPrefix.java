package leetcode.topmianshi.string;

/**
 * @author Kelly
 * @create 2020-05-27 11:23
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 */
public class T14_LongestCommonPrefix {
    /**
     * 以第一个元素作为基准，然后依次比较
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String res = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            for (; j < res.length() && j < strs[i].length(); j++) {
                if (res.charAt(j) != strs[i].charAt(j)) break;
            }
            res = res.substring(0,j);
            if (res.equals("")) return "";
        }
        return res;
    }

    // 用 String.indexOf, 返回此字符串中第一个出现的指定的子字符串，如果没有找到则返回 -1
    public static String longestCommonPrefix1(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String res = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(res) != 0){
                res = res.substring(0, res.length() - 1);  // 若当前子串更短，不断缩小范围
                if (res.equals("")) return "";
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] strings = new String[]{ "fl","floor","", "flood"};
        String res = longestCommonPrefix1(strings);
        System.out.println(res);
    }

    /**
     * 可以用字典树，太麻烦了
     */

}
