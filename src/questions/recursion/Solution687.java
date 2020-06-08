package questions.recursion;

import pojo.TreeNode;

/**
 * 最长同值路径
 *
 * @author : xianzilei
 * @date : 2020/6/8 13:16
 */
public class Solution687 {
    private static int length = 0;

    public static int longestUnivaluePath(TreeNode root) {

        return 1;
    }

//    private static int dfs(TreeNode node) {
//        if (node == null) {
//            return 0;
//        }
//        int left = dfs(node.left);
//        int right = dfs(node.right);
//
//
//    }

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
