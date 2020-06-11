package solutions.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 电话号码的字母组合--TODO
 *
 * @author : xianzilei
 * @date : 2020/6/10 13:04
 */
public class Solution17 {
    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        Map<Character, String> dicMap = new HashMap<>();
        dicMap.put('2', "abc");
        dicMap.put('3', "def");
        dicMap.put('4', "ghi");
        dicMap.put('5', "jkl");
        dicMap.put('6', "mno");
        dicMap.put('7', "pqrs");
        dicMap.put('8', "tuv");
        dicMap.put('9', "wxyz");
        char[] chars = digits.toCharArray();
        backtrack(0, chars, dicMap, result);
        return result;
    }

    private static void backtrack(int level, char[] chars, Map<Character, String> dicMap, List<String> result) {
        if (level == chars.length) {
            return;
        }
        if (result.size() - 1 < level) {
            result.add("");
        }
        String cur = result.get(level);
        String s = dicMap.get(chars[level]);
        for (int i = 0; i < s.length(); i++) {
            result.set(level, cur + s.charAt(i));
            backtrack(level + 1, chars, dicMap, result);
        }

    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }
}
