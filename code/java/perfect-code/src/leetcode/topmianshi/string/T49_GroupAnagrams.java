package leetcode.topmianshi.string;

import java.util.*;

/**
 * @author Kelly
 * @create 2020-06-21 10:18
 *
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 */
public class T49_GroupAnagrams {
    /**
     * 怎么查找字母异位词
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return null;
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);   // 对字符数组排序

            String key = Arrays.toString(charArray);
            if (!map.containsKey(key)) map.put(key, new ArrayList<>());
            map.get(key).add(str);
        }
        List<List<String>> res = new ArrayList<>(map.values());
        return res;
    }
}
