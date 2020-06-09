package solutions.stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * 有效的括号
 *
 * @author : xianzilei
 * @date : 2019/12/16 08:56
 */
public class Solution20 {

    public static boolean isValid(String s) {
        if (s == null || "".equals(s)) {
            return true;
        }
        char[] chars = s.toCharArray();
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Stack<Character> stack = new Stack<>();
        for (char c : chars) {
            if (map.containsKey(c)) {
                if (!stack.empty()) {
                    if (map.get(c).equals(stack.peek())) {
                        stack.pop();
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.empty();
    }

    public static void main(String[] args) {
        boolean b1 = isValid("(){}[]");
        boolean b2 = isValid("([");
        System.out.println(b1);
        System.out.println(b2);
    }
}
