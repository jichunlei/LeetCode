package solutions.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成--TODO
 *
 * @author : xianzilei
 * @date : 2020/6/15 20:09
 */
public class Solution22 {

    /**
     * 解法一：暴力法（递归写法）
     *
     * @param n 1
     * @return java.util.List<java.lang.String>
     * @author xianzilei
     * @date 2020/6/16 20:00
     **/
    public static List<String> generateParenthesis1(int n) {
        List<String> result = new ArrayList<>();
        recursion("", n, result);
        return result;
    }

    private static void recursion(String str, int n, List<String> result) {
        //穷举每种结果，判断是否满足需求
        if (str.length() == n * 2) {
            //如果满足则添加到结果集中
            if (isValid(str)) {
                result.add(str);
            }
            return;
        }
        //递归添加左括号
        recursion(str + "(", n, result);
        //递归添加左括号
        recursion(str + ")", n, result);
    }

    //合法性校验（每个位置下左括号数量必须大于等于右括号数量，且左右括号的总数量必须一致）
    private static boolean isValid(String str) {
        char[] chars = str.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                count++;
            }
            if (chars[i] == ')') {
                count--;
            }
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }


    /**
     * 解法二：回溯法
     *
     * @param n 1
     * @return java.util.List<java.lang.String>
     * @author xianzilei
     * @date 2020/6/16 20:06
     **/
    public static List<String> generateParenthesis2(int n) {
        //结果集
        List<String> result = new ArrayList<>();
        //回溯法
        backtrack("", 0, 0, n, result);
        //返回结果集
        return result;
    }

    /**
     * @param str    当前递归得到的结果
     * @param left   左括号已经使用了几个
     * @param right  右括号已经使用了几个
     * @param n      左右括号得使用几个
     * @param result 结果集
     **/
    private static void backtrack(String str, int left, int right, int n, List<String> result) {
        //终止条件，即左右括号都使用完全
        if (left == n && right == n) {
            //当前结果添加到结果集
            result.add(str);
            return;
        }
        //剪枝（左括号个数不能小于右括号个数）
        if (left < right) {
            return;
        }
        //如果左括号没有使用完，添加左括号
        if (left < n) {
            backtrack(str + '(', left + 1, right, n, result);
        }
        //如果右括号没有使用完，添加右括号
        if (right < n) {
            backtrack(str + ')', left, right + 1, n, result);
        }
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis1(3));
        System.out.println(generateParenthesis2(3));
    }
}
