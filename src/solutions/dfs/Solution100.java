package solutions.dfs;

import pojo.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 相同的树
 *
 * @author : xianzilei
 * @date : 2020/8/7
 */
public class Solution100 {
    /**
     * 解法一：DFS
     *
     * @param p 1
     * @param q 2
     * @return boolean
     * @author xianzilei
     * @date 2020/8/7 8:25
     **/
    public boolean isSameTreeDfs(TreeNode p, TreeNode q) {
        //判断当前节点是否满足条件
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        //递归判断当前节点的左右子节点
        return isSameTreeDfs(p.left, q.left) && isSameTreeDfs(p.right, q.right);
    }

    /**
     * 解法二：BFS
     *
     * @param p 1
     * @param q 2
     * @return boolean
     * @author xianzilei
     * @date 2020/8/7 8:43
     **/
    public boolean isSameTreeBfs(TreeNode p, TreeNode q) {
        //定义两个队列保存节点
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        //将根节点入队
        queue1.add(p);
        queue2.add(q);
        //遍历队列
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            //出队
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            //判断当前节点是否满足条件，如果满足则其左右子节点分别入队
            if (compareTreeNode(node1, node2)) {
                if (node1 != null) {
                    queue1.add(node1.left);
                    queue1.add(node1.right);
                    queue2.add(node2.left);
                    queue2.add(node2.right);
                }
            }
            //否则直接返回false
            else {
                return false;
            }
        }
        //遍历结束直接返回true
        return true;
    }

    //判断两个节点是否相等
    private boolean compareTreeNode(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        return node1.val == node2.val;
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        TreeNode node11 = new TreeNode(2);
        TreeNode node12 = new TreeNode(3);
        TreeNode node13 = new TreeNode(4);
        root1.left = node11;
        root1.right = node12;
        node12.right = node13;

        TreeNode root2 = new TreeNode(1);
        TreeNode node21 = new TreeNode(2);
        TreeNode node22 = new TreeNode(3);
        TreeNode node23 = new TreeNode(4);
        root2.left = node21;
        root2.right = node22;
        node22.right = node23;

        Solution100 solution100 = new Solution100();
        System.out.println(solution100.isSameTreeDfs(root1, root2));
        System.out.println(solution100.isSameTreeBfs(root1, root2));
    }
}
