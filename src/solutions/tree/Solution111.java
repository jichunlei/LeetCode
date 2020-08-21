package solutions.tree;

import pojo.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的最小深度
 *
 * @author : xianzilei
 * @date : 2020/8/21
 */
public class Solution111 {
    /**
     * 解法一：DFS
     *
     * @param root 1
     * @return int
     * @author xianzilei
     * @date 2020/8/21 8:44
     **/
    public int minDepthDfs(TreeNode root) {
        //本题就是求根节点到任一叶子节点的最短路径

        //根节点为空直接返回0
        if (root == null) {
            return 0;
        }
        //如果当前节点就是叶子节点，直接返回1
        if (root.left == null && root.right == null) {
            return 1;
        }
        //如果当前节点只有左子树，则返回右子树的最短路径+1
        if (root.left == null) {
            return minDepthDfs(root.right) + 1;
        }
        //如果当前节点只有右子树，则返回左子树的最短路径+1
        if (root.right == null) {
            return minDepthDfs(root.left) + 1;
        }
        //如果当前节点左右子树都存在，则返回左右子树的最短路径+1
        return Math.min(minDepthDfs(root.left), minDepthDfs(root.right)) + 1;
    }

    /**
     * 解法二：BFS
     *
     * @param root 1
     * @return int
     * @author xianzilei
     * @date 2020/8/21 8:47
     **/
    public int minDepthBfs(TreeNode root) {
        //类似层序遍历，遍历每一层的节点，一旦发现存在叶子节点直接返回层数，即为最短路径

        //特殊情况的排除
        if (root == null) {
            return 0;
        }
        //定义队列保存每一层的节点
        Queue<TreeNode> queue = new LinkedList<>();
        //根节点入队
        queue.offer(root);
        //定义层号
        int result = 0;
        while (!queue.isEmpty()) {
            //层号+1
            result++;
            //控制只出队同一层的节点
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                //出队
                TreeNode poll = queue.poll();
                //如果出队的节点是叶子节点，直接返回结果
                if (poll.left == null && poll.right == null) {
                    return result;
                }
                //否则就将当前节点的左右子节点入队
                else {
                    if (poll.left != null) {
                        queue.offer(poll.left);
                    }
                    if (poll.right != null) {
                        queue.offer(poll.right);
                    }
                }
            }
        }
        return 0;
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
        Solution111 solution111 = new Solution111();
        System.out.println("二叉树的最小高度（DFS）：" + solution111.minDepthDfs(root));
        System.out.println("二叉树的最小高度（BFS）：" + solution111.minDepthBfs(root));
    }
}
