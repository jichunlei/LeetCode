package solutions.tree;

import pojo.TreeNode;

import java.util.*;

/**
 * 二叉树的后序遍历
 *
 * @author : xianzilei
 * @date : 2020/9/29 8:34
 */
public class Solution145 {
    /**
     * 解法一：递归法
     *
     * @param root 1
     * @return java.util.List<java.lang.Integer>
     * @author xianzilei
     * @date 2020/9/29 9:03
     **/
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }

    //递归
    private void dfs(TreeNode node, List<Integer> result) {
        //递归结束条件
        if (node == null) {
            return;
        }
        //遍历左子树
        dfs(node.left, result);
        //遍历右子树
        dfs(node.right, result);
        //遍历根节点
        result.add(node.val);
    }

    /**
     * 解法二：迭代法
     *
     * @param root 1
     * @return java.util.List<java.lang.Integer>
     * @author xianzilei
     * @date 2020/9/29 9:05
     **/
    public List<Integer> postorderTraversal2(TreeNode root) {
        //前序遍历：根左右
        //后序遍历：左右根
        //逆后序遍历：根右左，即后续遍历的反向顺序
        //所以前序遍历时左右子节点入栈的顺序反过来即可得逆后续遍历

        List<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        //定义栈存放待遍历的节点
        Stack<TreeNode> stack = new Stack<>();
        //根节点入栈
        stack.push(root);
        //按照根右左顺序进行遍历
        while (!stack.isEmpty()) {
            //出栈
            TreeNode node = stack.pop();
            //遍历当前节点（注意此处为头插法）
            result.add(0, node.val);
            //左子树入栈
            if (node.left != null) {
                stack.push(node.left);
            }
            //右子树入栈
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.right = node2;
        node2.left = node3;
        Solution145 solution145 = new Solution145();
        System.out.println(solution145.postorderTraversal2(node1));
        //3,2,1
    }
}
