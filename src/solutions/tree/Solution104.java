package solutions.tree;

import pojo.TreeNode;

import java.util.LinkedList;

/**
 * 二叉树的最大深度
 *
 * @author : xianzilei
 * @date : 2020/7/28
 */
public class Solution104 {

    /**
     * 解法一：DFS
     *
     * @param root 1
     * @return int
     * @author xianzilei
     * @date 2020/7/28 8:35
     **/
    public static int maxDepthDfs(TreeNode root) {
        //递归方法定义：以当前节点作为根节点的最大高度
        //递归终止条件
        if (root == null) {
            return 0;
        }
        //求左子树的最大高度
        int leftMaxDepth = maxDepthDfs(root.left);
        //求右子树的最大高度
        int rightMaxDepth = maxDepthDfs(root.right);
        //结果即为左右子树的最大值+1
        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
    }

    /**
     * 解法二：BFS
     *
     * @param root 1
     * @return int
     * @author xianzilei
     * @date 2020/7/28 8:37
     **/
    public static int maxDepthBfs(TreeNode root) {
        //如果根节点为空，直接返回
        if (root == null) {
            return 0;
        }
        //定义队列存放每一层的节点
        LinkedList<TreeNode> queue = new LinkedList<>();
        //根节点入队
        queue.addLast(root);
        int depth = 0;
        //当队列不为空时
        while (!queue.isEmpty()) {
            //每次深度加1
            depth++;
            //这里先取出长度，控制出队的始终是同一层的节点
            int size = queue.size();
            //讲该层的节点全部出对，并将它们的子节点入队
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.removeFirst();
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
        }
        return depth;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(8);
        TreeNode t1 = new TreeNode(7);
        TreeNode t2 = new TreeNode(13);
        TreeNode t3 = new TreeNode(10);
        TreeNode t4 = new TreeNode(6);
        TreeNode t5 = new TreeNode(3);
        TreeNode t6 = new TreeNode(1);
        root.left = t1;
        root.right = t2;
        t1.left = t3;
        t2.right = t4;
        t4.left = t5;
        t4.right = t6;
        System.out.println("二叉树的高（DFS）：" + maxDepthDfs(root));
        System.out.println("二叉树的高（BFS）：" + maxDepthBfs(root));
    }
}
