package solutions.tree;

import pojo.TreeNode;

/**
 * 把二叉搜索树转换为累加树--TODO
 *
 * @author : xianzilei
 * @date : 2020/9/21 8:18
 */
public class Solution538 {

    /**
     * 记录中序遍历的后序和
     **/
    int sum = 0;

    /**
     * 解法一：反中序遍历
     *
     * @param root 1
     * @return pojo.TreeNode
     * @author xianzilei
     * @date 2020/9/21 10:04
     **/
    public TreeNode convertBST(TreeNode root) {
        //由于给定的树为二叉搜索树，其中序遍历是递增序列，
        //所以反中序遍历为递减序列，所以可以反中序遍历，每次记录累加和

        reverseInOrder(root);
        return root;
    }

    /**
     * 反中序遍历
     **/
    private void reverseInOrder(TreeNode node) {
        //递归终止条件
        if (node == null) {
            return;
        }
        //遍历右子树
        reverseInOrder(node.right);
        //记录当前和
        sum += node.val;
        //赋值当前节点
        node.val = sum;
        //遍历左子树
        reverseInOrder(node.left);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(13);
        root.left = node1;
        root.right = node2;
        Solution538 solution538 = new Solution538();
        TreeNode result = solution538.convertBST(root);
        System.out.println(result.val);
        System.out.println(result.left.val);
        System.out.println(result.right.val);
        //18 20 13
    }


}
