package solutions.tree;

import pojo.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的前序遍历
 *
 * @author : xianzilei
 * @date : 2020/10/27 8:10
 */
public class Solution144 {
    /**
     * 解法一：递归法
     *
     * @param root 1
     * @return java.util.List<java.lang.Integer>
     * @author xianzilei
     * @date 2020/10/27 8:15
     **/
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }

    private void preorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        result.add(node.val);
        preorderHelper(node.left, result);
        preorderHelper(node.right, result);
    }

    /**
     * 解法二：非递归写法
     *
     * @param root 1
     * @return java.util.List<java.lang.Integer>
     * @author xianzilei
     * @date 2020/10/27 8:15
     **/
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }
        }
        return result;
    }
}
