package solutions.recursion;

import pojo.TreeNode;

/**
 * 最长同值路径
 *
 * @author : xianzilei
 * @date : 2020/6/8 13:16
 */
public class Solution687 {
    private static int length = 0;

    /**
     * 递归
     *
     * @param root 1
     * @return int
     * @author xianzilei
     * @date 2020/6/9 8:31
     **/
    public static int longestUnivaluePath(TreeNode root) {
        arrowLength(root);
        return length;
    }

    /**
     * 1、方法的作用：以node为出发点延伸出的最大箭头长度
     * 2、递归结束条件：node==null，返回0
     * 3、等价关系式：
     * 3.1 当node.val=node.left.val时,arrowLength(node)=arrowLength(node.left)+1
     * 3.2 当node.val=node.right.val,arrowLength(node)=arrowLength(node.right)+1
     **/
    private static int arrowLength(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftArrowLength = arrowLength(node.left);
        int rightArrowLength = arrowLength(node.right);
        int left = 0, right = 0;
        if (node.left != null && node.val == node.left.val) {
            left = leftArrowLength + 1;
        }
        if (node.right != null && node.val == node.right.val) {
            right = rightArrowLength + 1;
        }
        length = Math.max(length, left + right);
        return Math.max(left, right);
    }

    public static void main(String[] args) {
        /**
         *     1
         *    / \
         *   4   5
         *  / \   \
         * 4   4   5
         **/
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.right = node5;
        System.out.println(longestUnivaluePath(root));
    }
}
