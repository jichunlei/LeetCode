package solutions.tree;

import pojo.TreeNode;

/**
 * 合并二叉树
 *
 * @author : xianzilei
 * @date : 2020/9/23 8:34
 */
public class Solution617 {
    /**
     * 解法：遍历合并
     *
     * @param t1 1
     * @param t2 2
     * @return pojo.TreeNode
     * @author xianzilei
     * @date 2020/9/23 8:47
     **/
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        //t1为空，直接返回t2
        if (t1 == null) {
            return t2;
        }
        //t2为空，直接返回t1
        if (t2 == null) {
            return t1;
        }
        //定义根节点
        TreeNode root = new TreeNode();
        root.val = t1.val + t2.val;
        //递归合并左子树
        root.left = mergeTrees(t1.left, t2.left);
        //递归合并右子树
        root.right = mergeTrees(t1.right, t2.right);
        return root;
    }
}
