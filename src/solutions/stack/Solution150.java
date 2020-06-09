package solutions.stack;

import java.util.LinkedList;

/**
 * 逆波兰表达式求值
 *
 * @author : xianzilei
 * @date : 2020/6/1 15:47
 */
public class Solution150 {

    /**
     * 功能描述: 栈解决
     *
     * @param tokens 1
     * @return int
     * @author xianzilei
     * @date 2020/6/1 16:06
     **/
    public static int evalRPN(String[] tokens) {
        LinkedList<Integer> stack = new LinkedList<>();
        Integer op1;
        Integer op2;
        for (int i = 0; i < tokens.length; i++) {
            String str = tokens[i];
            if ("+".equals(str)) {
                op1 = stack.removeLast();
                op2 = stack.removeLast();
                stack.addLast(op2 + op1);
            } else if ("-".equals(str)) {
                op1 = stack.removeLast();
                op2 = stack.removeLast();
                stack.addLast(op2 - op1);
            } else if ("*".equals(str)) {
                op1 = stack.removeLast();
                op2 = stack.removeLast();
                stack.addLast(op2 * op1);
            } else if ("/".equals(str)) {
                op1 = stack.removeLast();
                op2 = stack.removeLast();
                stack.addLast(op2 / op1);
            } else {
                stack.addLast(Integer.valueOf(str));
            }
        }
        return stack.remove();
    }

    public static void main(String[] args) {
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
//        String[] tokens = {"4", "13", "5", "/", "+"};
        System.out.println(evalRPN(tokens));
    }
}
