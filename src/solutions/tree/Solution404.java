package solutions.tree;

import pojo.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 左叶子之和
 *
 * @author : xianzilei
 * @date : 2020/9/19 19:17
 */
public class Solution404 {

    /**
     * 解法一：DFS
     *
     * @param root 1
     * @return int
     * @author xianzilei
     * @date 2020/9/19 19:45
     **/
    public int sumOfLeftLeavesDfs(TreeNode root) {
        //递归函数定义：以当前节点为根节点的二叉树的左叶子节点之和
        //对于每一棵二叉树，左叶子节点的和等于左右子树的左叶子节点之和，正好符合递归的特点

        //节点为空，直接返回0
        if (root == null) {
            return 0;
        }
        //左节点为空，则取右子树的结果
        if (root.left == null) {
            return sumOfLeftLeavesDfs(root.right);
        }
        //左子树的左孩子为叶子节点，则取右子树的结果+左子树的左孩子值
        if (root.left.left == null && root.left.right == null) {
            return root.left.val + sumOfLeftLeavesDfs(root.right);
        }
        //左子树的结果+右子树的结果
        return sumOfLeftLeavesDfs(root.left) + sumOfLeftLeavesDfs(root.right);
    }

    /**
     * 解法二：DFS
     *
     * @param root 1
     * @return int
     * @author xianzilei
     * @date 2020/9/19 20:41
     **/
    public int sumOfLeftLeavesBfs(TreeNode root) {
        //如果根节点为空或者根节点为叶子节点，直接返回空
        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int result = 0;
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            //如果左节点不为空
            if (node.left != null) {
                //如果左节点的左节点为叶子节点，则累加值
                if (node.left.left == null && node.left.right == null) {
                    result += node.left.val;
                }
                //否则入队
                else {
                    queue.offer(node.left);
                }
            }
            //右节点不为空，入队
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        //返回结果
        return result;
    }
}
