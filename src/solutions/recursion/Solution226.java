package solutions.recursion;

import pojo.TreeNode;

/**
 * 翻转二叉树
 *
 * @author : xianzilei
 * @date : 2020/9/16 8:06
 */
public class Solution226 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = left;
        return root;
    }
}
