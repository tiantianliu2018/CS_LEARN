package leetcode.topmianshi.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kelly
 * @create 2020-05-28 10:27
 *
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内.
 */
public class T13_Roman2Integer {
    public static void main(String[] args) {
        int res = romanToInt("III");
        System.out.println(res);
    }
    public static int romanToInt(String s) {
        if (s == null || s.length() == 0) return 0;
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int res = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (map.get(s.charAt(i)) < map.get(s.charAt(i + 1)))
                res -= map.get(s.charAt(i));
            else res += map.get(s.charAt(i));
        }
        res += map.get(s.charAt(s.length() - 1));
        return res;
    }

    public int romanToInt1(String s) {
        if (s == null || s.length() == 0) return 0;
        int res = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (getValue(s.charAt(i)) < getValue(s.charAt(i + 1)))
                res -= getValue(s.charAt(i));
            else res += getValue(s.charAt(i));
        }
        res += getValue(s.charAt(s.length() - 1));
        return res;
    }

    private int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
}
