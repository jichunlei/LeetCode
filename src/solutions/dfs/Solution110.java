package solutions.dfs;

import pojo.TreeNode;

/**
 * 平衡二叉树
 *
 * @author : xianzilei
 * @date : 2020/8/17
 */
public class Solution110 {
    /**
     * 解法：DFS
     *
     * @param root 1
     * @return boolean
     * @author xianzilei
     * @date 2020/8/17 8:17
     **/
    public boolean isBalanced(TreeNode root) {
        //如果节点为空，直接返回true
        if (root == null) {
            return true;
        }
        //如果当前节点的左右子节点高度差大于1，则返回false
        if (Math.abs(getHeight(root.left) - getHeight(root.right)) > 1) {
            return false;
        }
        //递归遍历左右子树的平衡性
        return isBalanced(root.left) && isBalanced(root.right);
    }

    //求当前节点的高度
    private int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }


}
