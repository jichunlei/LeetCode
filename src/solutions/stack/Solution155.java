package solutions.stack;

import pojo.MinStack;

/**
 * 最小栈
 *
 * @author : xianzilei
 * @date : 2019/12/16 18:34
 */
public class Solution155 {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        System.out.println();
    }
}
