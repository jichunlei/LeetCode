package questions.stack;

import questions.pojo.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的中序遍历
 *
 * @author : xianzilei
 * @date : 2019/12/16 18:43
 */
public class Question94 {
    /**
     * 功能描述: 递归写法
     *
     * @param root 1
     * @return java.util.List<java.lang.Integer>
     * @author xianzilei
     * @date 2019/12/16 19:21
     **/
    public static List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            fun(root, list);
        }
        return list;
    }

    private static void fun(TreeNode node, List<Integer> list) {
        if (node.left != null) {
            fun(node.left, list);
        }
        list.add(node.val);
        if (node.right != null) {
            fun(node.right, list);
        }

    }

    /**
     * 功能描述: 非递归写法
     *
     * @param root 1
     * @return java.util.List<java.lang.Integer>
     * @author xianzilei
     * @date 2019/12/16 19:38
     **/
    public static List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            list.add(node.val);
            node = node.right;
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
