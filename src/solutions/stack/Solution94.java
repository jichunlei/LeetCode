package solutions.stack;

import pojo.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的中序遍历
 *
 * @author : xianzilei
 * @date : 2019/12/16 18:43
 */
public class Solution94 {
    /**
     * 解法一：递归写法
     *
     * @param root 1
     * @return java.util.List<java.lang.Integer>
     * @author xianzilei
     * @date 2019/12/16 19:21
     **/
    public static List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            dfs(root, result);
        }
        return result;
    }

    /**
     * 功能描述: 递归写法
     *
     * @param node   1
     * @param result 2
     * @return void
     * @author xianzilei
     * @date 2019/12/30 8:42
     **/
    private static void dfs(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        dfs(node.left, result);
        result.add(node.val);
        dfs(node.right, result);
    }

    /**
     * 解法二：非递归写法（使用栈）
     *
     * @param root 1
     * @return java.util.List<java.lang.Integer>
     * @author xianzilei
     * @date 2019/12/16 19:38
     **/
    public static List<Integer> inorderTraversal2(TreeNode root) {
        //结果集
        List<Integer> result = new ArrayList<>();
        //定义栈记忆节点
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            result.add(node.val);
            node = node.right;
        }
        return result;
    }


    /**
     * 解法三：莫里斯遍历--TODO
     *
     * @param root 1
     * @return java.util.List<java.lang.Integer>
     * @author xianzilei
     * @date 2019/12/30 18:33
     **/
    public static List<Integer> Morris(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            if (root.left != null) {

            }
        }


        return list;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode r2 = new TreeNode(2);
        TreeNode l3 = new TreeNode(3);
        root.right = r2;
        r2.left = l3;
        System.out.println(inorderTraversal1(root));
        System.out.println(inorderTraversal2(root));
    }
}
