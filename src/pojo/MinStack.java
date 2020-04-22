package pojo;

import java.util.Stack;

/**
 * 最小栈
 *
 * @author : xianzilei
 * @date : 2019/12/16 18:23
 */
public class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> tempStack;

    public MinStack() {
        stack = new Stack<>();
        tempStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (tempStack.empty()) {
            tempStack.push(x);
        } else {
            Integer peek = tempStack.peek();
            if (peek > x) {
                tempStack.push(x);
            } else {
                tempStack.push(peek);
            }
        }
    }

    public void pop() {
        stack.pop();
        tempStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return tempStack.peek();
    }
}
