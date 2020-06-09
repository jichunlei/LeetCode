package solutions.stack;

import java.util.LinkedList;

/**
 * 字符串解码
 *
 * @author : xianzilei
 * @date : 2020/4/22 15:03
 */
public class Solution394 {
    /**
     * 功能描述: 解法一--双栈法
     *
     * @param s 1
     * @return java.lang.String
     * @author xianzilei
     * @date 2020/4/22 18:55
     **/
    public static String decodeString1(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        //
        LinkedList<Integer> stack_multi = new LinkedList<>();
        LinkedList<String> stack_res = new LinkedList<>();
        for (Character c : s.toCharArray()) {
            if (c == '[') {
                stack_multi.addLast(multi);
                stack_res.addLast(res.toString());
                multi = 0;
                res = new StringBuilder();
            } else if (c == ']') {
                StringBuilder tmp = new StringBuilder();
                int cur_multi = stack_multi.removeLast();
                for (int i = 0; i < cur_multi; i++) {
                    tmp.append(res);
                }
                res = new StringBuilder(stack_res.removeLast() + tmp);
            } else if (c >= '0' && c <= '9') {
                multi = multi * 10 + Integer.parseInt(c + "");
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

    /**
     * 功能描述: 解法二--单栈法
     *
     * @param s 1
     * @return java.lang.String
     * @author xianzilei
     * @date 2020/4/22 18:56
     **/
    public static String decodeString2(String s) {
        StringBuilder res = new StringBuilder();
        //使用LinkedList来模拟栈结构
        LinkedList<Character> stack = new LinkedList<>();
        //循环遍历字符串
        for (Character c : s.toCharArray()) {
            //当遍历到]时，需要消除[]并计算累计的字符串
            if (c == ']') {
                //存放需要叠加的字符串
                StringBuilder tempStr = new StringBuilder();
                //向前查找直到出现[
                do {
                    tempStr.append(stack.removeLast());
                } while (stack.removeLast() != '[');
                //去除[
                stack.removeLast();
                //存放需要叠加的次数
                Integer temp = 0;
                //多位数字的处理
                int k = 1;
                //向前查找直到不出现数字
                do {
                    temp = temp + Integer.parseInt(stack.removeLast() + "") * k;
                    k = k * 10;
                } while (!stack.isEmpty() && stack.getLast() >= '0' && stack.getLast() <= '9');
                //将字符串累计添加到栈中
                char[] chars = tempStr.toString().toCharArray();
                for (int i = 0; i < temp; i++) {
                    for (int j = chars.length - 1; j >= 0; j--) {
                        stack.addLast(chars[j]);
                    }
                }
            }
            //如果不是]，直接入栈
            else {
                stack.addLast(c);
            }
        }

        //将栈元素拼接成字符串
        for (Character character : stack) {
            res.append(character);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String s1 = "3[a]2[bc]";
        System.out.println(s1 + "==>" + decodeString1(s1));
        System.out.println(s1 + "==>" + decodeString2(s1));
        String s2 = "3[a2[c]]";
        System.out.println(s2 + "==>" + decodeString1(s2));
        System.out.println(s2 + "==>" + decodeString2(s2));
        String s3 = "100[leetcode]";
        System.out.println(s3 + "==>" + decodeString1(s3));
        System.out.println(s3 + "==>" + decodeString2(s3));
    }
}
