package solutions.dfs;

import pojo.TreeNode;

/**
 * 二叉树中的最大路径和
 *
 * @author : xianzilei
 * @date : 2020/6/22 08:15
 */
public class Solution124 {

    private static int result = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return result;
    }

    //以node节点为首的路径（往下）的最大值
    private static int dfs(TreeNode node) {
        //递归终止条件
        if (node == null) {
            return 0;
        }
        //查找左分支的最大路径和，如果为负数直接抛弃，返回0
        int left = Math.max(0, dfs(node.left));
        //查找右分支的最大路径和，如果为负数直接抛弃，返回0
        int right = Math.max(0, dfs(node.right));
        //更新最大值
        result = Math.max(result, node.val + left + right);
        //返回以node为首的向下路径的最大值
        return node.val + Math.max(left, right);
    }

    public static void main(String[] args) {
        //-10,9,20,null,null,15,7
        //         -10
        //         / \
        //        9   20
        //           /  \
        //          15   7
        TreeNode root = new TreeNode(-10);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;
        System.out.println(maxPathSum(root));
    }
}
