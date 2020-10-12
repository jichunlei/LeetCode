package solutions.tree;

import pojo.TreeNode;

/**
 * 二叉搜索树的最小绝对差
 *
 * @author : xianzilei
 * @date : 2020/10/12 8:10
 */
public class Solution530 {
    int result = Integer.MAX_VALUE;
    int pre = -1;

    /**
     * 解法：中序遍历
     *
     * @param root 1
     * @return int
     * @author xianzilei
     * @date 2020/10/12 8:23
     **/
    public int getMinimumDifference(TreeNode root) {
        //中序遍历
        dfs(root);
        return result;
    }

    //中序遍历
    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        //遍历左子树
        dfs(node.left);
        //遍历当前节点
        //更新差值
        if (pre >= 0 && node.val - pre < result) {
            result = node.val - pre;
        }
        //保存当前节点
        pre = node.val;
        //遍历右子树
        dfs(node.right);
    }

}
