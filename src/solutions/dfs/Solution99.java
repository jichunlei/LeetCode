package solutions.dfs;

import pojo.TreeNode;

/**
 * 恢复二叉搜索树--TODO
 *
 * @author : xianzilei
 * @date : 2020/8/8 10:40
 */
public class Solution99 {
    public void recoverTree(TreeNode root) {
        dfs(root, null);
    }

    private void dfs(TreeNode node, TreeNode pre) {
        if(node==null){
            return;
        }
        
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(2);
        root1.left = node2;
        node2.right = node3;
        Solution99 solution99 = new Solution99();
        solution99.recoverTree(root1);
    }
}
