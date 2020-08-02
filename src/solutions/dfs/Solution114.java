package solutions.dfs;

import pojo.Codec;
import pojo.TreeNode;

/**
 * 二叉树展开为链表
 *
 * @author : xianzilei
 * @date : 2020/8/2 10:16
 */
public class Solution114 {
    /**
     * 解法一：DFS
     *
     * @param root 1
     * @return void
     * @author xianzilei
     * @date 2020/8/2 10:48
     **/
    public static void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root, null);
    }

    //处理节点node（清空左右子树），并链接到前驱节点上
    //node：当前节点
    //pre：当前节点的前驱节点
    private static void dfs(TreeNode node, TreeNode pre) {
        //根据前序遍历的顺序，先处理根节点，再处理左子树，最后处理右子树

        //先保存一下左右子树
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;
        //清空当前节点的左右子树
        node.left = null;
        node.right = null;
        //如果前驱不为空，将当前节点链接到前驱上
        if (pre != null) {
            pre.right = node;
        }
        //遍历左子树，左子树的前驱肯定是当前节点
        if (leftNode != null) {
            dfs(leftNode, node);
        }
        //遍历右子树，右子树的前驱是其根节点或者根节点的左子树的前序遍历的最后一个节点
        //这里因为对于当前节点已经处理完了其左子树的所有节点，所以只需找到当前节点的右子树的右子树的右子树...，
        //即为所需要的前驱节点
        if (rightNode != null) {
            //查找当前节点的前驱节点
            TreeNode tmpPre = node;
            while (tmpPre.right != null) {
                tmpPre = tmpPre.right;
            }
            dfs(rightNode, tmpPre);
        }
    }

    public static void main(String[] args) {
        //-10,9,20,null,null,15,7
        //         1
        //        / \
        //       2   5
        //      / \   \
        //     3   4   6
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(6);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;
        flatten(root);
        Codec codec = new Codec();
        System.out.println(codec.serialize(root));

    }
}
