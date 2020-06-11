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
    //结果集
    private static List<String> result = new ArrayList<>();
    private static Map<Character, String> dicMap = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};

    public static List<String> letterCombinations(String digits) {

        StringBuilder track = new StringBuilder();
        boolean[] visited = new boolean[digits.length()];
        backtrack(digits, "", 0);
        return result;
    }

    private static void backtrack(String digits, String str, int index) {
        if(str.length()==digits.length()){
            result.add(str);
            return;
        }

        for (int i = 0; i < digits.length(); i++) {

        }
    }


    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
        //"ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"
    }
}
