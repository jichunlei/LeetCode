package solutions.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 有效的字母异位词
 *
 * @author : xianzilei
 * @date : 2020/11/23 8:22
 */
public class Solution242 {
    /**
     * 解法一：数组定位法
     *
     * @param s 1
     * @param t 2
     * @return boolean
     * @author xianzilei
     * @date 2020/11/23 8:25
     **/
    public boolean isAnagram(String s, String t) {
        //题目说只包含小写字母，因此可以定义26位的数组来当做哈希表
        if (s.length() != t.length()) {
            return false;
        }
        int[] array = new int[26];
        for (int i = 0; i < s.length(); i++) {
            array[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            int index = t.charAt(i) - 'a';
            if (array[index] == 0) {
                return false;
            }
            array[index]--;
        }
        return true;
    }

    /**
     * 解法二：哈希表
     *
     * @param s 1
     * @param t 2
     * @return boolean
     * @author xianzilei
     * @date 2020/11/23 8:26
     **/
    public boolean isAnagram2(String s, String t) {
        //考虑到题目的进阶说法（可能包含 unicode 字符），因此可以采用统用的哈希表来存储元素进行比对
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            Integer count = map.get(ch);
            if (count == null || count == 0) {
                return false;
            }
            map.put(ch, count - 1);
        }
        return true;
    }

    /**
     * 解法三：排序法
     *
     * @param s 1
     * @param t 2
     * @return boolean
     * @author xianzilei
     * @date 2020/11/23 8:31
     **/
    public boolean isAnagram3(String s, String t) {
        //将字符串进行排序后比较是否相等
        if (s.length() != t.length()) {
            return false;
        }
        char[] arg1 = s.toCharArray();
        char[] arg2 = t.toCharArray();
        Arrays.sort(arg1);
        Arrays.sort(arg2);
        return Arrays.equals(arg1, arg2);
    }
}
