package solutions.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 电话号码的字母组合
 *
 * @author : xianzilei
 * @date : 2020/6/10 13:04
 */
public class Solution17 {

    /**
     * 回溯法
     *
     * @param digits 1
     * @return java.util.List<java.lang.String>
     * @author xianzilei
     * @date 2020/6/12 8:11
     **/
    public static List<String> letterCombinations1(String digits) {
        //结果集
        List<String> result = new ArrayList<>();
        //字典表
        Map<Character, String> dicMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        //回溯
        //track表示每次的路径
        StringBuilder track = new StringBuilder();
        backtrack1(digits, track, 0, result, dicMap);
        //返回结果
        return result;
    }

    //>>>>回溯的核心<<<<<
    //digits 选择列表
    //track  每次的路径
    //index  决策层数
    //result 结果集
    //dicMap 字典表
    private static void backtrack1(String digits, StringBuilder track, int index, List<String> result,
                                   Map<Character, String> dicMap) {
        //特殊情况下的处理
        if (digits == null || digits.length() == 0) {
            return;
        }
        //当决策层到最底层时，即本次完整路径已生成，将结果添加到结果集中，退出递归
        if (index == digits.length()) {
            result.add(track.toString());
            return;
        }
        //获取本次的可选择分支
        char num = digits.charAt(index);
        String letter = dicMap.get(num);
        //循环每个分支
        for (int i = 0; i < letter.length(); i++) {
            //做选择
            track.append(letter.charAt(i));
            //进入下一层决策树
            backtrack1(digits, track, index + 1, result, dicMap);
            //回溯
            track.deleteCharAt(track.length() - 1);
        }
    }

    /**
     * 回溯法（使用String的不可变性，无需回溯）
     *
     * @param digits 1
     * @return java.util.List<java.lang.String>
     * @author xianzilei
     * @date 2020/6/12 8:19
     **/
    public static List<String> letterCombinations2(String digits) {
        //结果集
        List<String> result = new ArrayList<>();
        //字典表
        Map<Character, String> dicMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack2(digits, "", 0, result, dicMap);
        return result;
    }

    private static void backtrack2(String digits, String track, int index, List<String> result,
                                   Map<Character, String> dicMap) {
        if (digits == null || digits.length() == 0) {
            return;
        }
        if (index == digits.length()) {
            result.add(track);
            return;
        }
        char num = digits.charAt(index);
        String letter = dicMap.get(num);
        for (int i = 0; i < letter.length(); i++) {
            //这里不用回溯的原因是String的不可变的特性
            backtrack2(digits, track + letter.charAt(i), index + 1, result, dicMap);
        }
    }


    public static void main(String[] args) {
        System.out.println(letterCombinations1(""));
        System.out.println(letterCombinations2("23"));
        //"ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"
    }
}
