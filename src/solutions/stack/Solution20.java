package solutions.stack;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 有效的括号
 *
 * @author : xianzilei
 * @date : 2019/12/16 08:56
 */
public class Solution20 {

    /**
     * 解法：栈
     *
     * @param s 1
     * @return boolean
     * @author xianzilei
     * @date 2020/8/14 8:33
     **/
    public static boolean isValid(String s) {
        //特殊情况下的排除
        if (s == null || "".equals(s)) {
            return true;
        }
        HashMap<Character, Character> map = new HashMap<>();
        map.put('}', '{');
        map.put(')', '(');
        map.put(']', '[');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //如果是{、(、[这三种，直接入栈
            if (!map.containsKey(c)) {
                stack.push(c);
            }
            //如果是}、]、)这三种，直接出栈
            else {
                //如果此时栈是空的或者出栈的元素与当前字符无法匹配，直接返回false
                if (stack.isEmpty() || !stack.pop().equals(map.get(c))) {
                    return false;
                }
            }
        }
        //如果栈为空则表示匹配成功，否则匹配失败
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        boolean b1 = isValid("(){}[]");
        boolean b2 = isValid("([");
        System.out.println(b1);
        System.out.println(b2);
    }
}
