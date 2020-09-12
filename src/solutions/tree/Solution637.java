package solutions.tree;

import pojo.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的层平均值
 *
 * @author : xianzilei
 * @date : 2020/9/12 13:47
 */
public class Solution637 {
    /**
     * 解法一：BFS
     *
     * @param root 1
     * @return java.util.List<java.lang.Double>
     * @author xianzilei
     * @date 2020/9/12 13:48
     **/
    public List<Double> averageOfLevelsBfs(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(sum / size);
        }
        return result;
    }

    /**
     * 解法二：DFS
     *
     * @param root 1
     * @return java.util.List<java.lang.Double>
     * @author xianzilei
     * @date 2020/9/12 13:55
     **/
    public List<Double> averageOfLevelsDfs(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<List<Double>> tmpList = new ArrayList<>();
        dfs(0, root, tmpList);
        for (List<Double> tmp : tmpList) {
            result.add(tmp.get(0) / tmp.get(1));
        }
        return result;
    }

    private void dfs(int level, TreeNode node, List<List<Double>> tmpList) {
        if (tmpList.size() <= level) {
            List<Double> list = new ArrayList<>(2);
            list.add(0d);
            list.add(0d);
            tmpList.add(list);
        }
        List<Double> curList = tmpList.get(level);
        curList.set(0, curList.get(0) + node.val);
        curList.set(1, curList.get(1) + 1);
        if (node.left != null) {
            dfs(level + 1, node.left, tmpList);
        }
        if (node.right != null) {
            dfs(level + 1, node.right, tmpList);
        }
    }

    public static void main(String[] args) {
        /*
         *        5
         *       / \
         *     4     8
         *    /      /\
         *   11    13  4
         *   /\         \
         *  7  2         1
         */
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(8);
        TreeNode node3 = new TreeNode(11);
        TreeNode node4 = new TreeNode(13);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(2);
        TreeNode node8 = new TreeNode(1);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node5.right = node8;
        Solution637 solution637 = new Solution637();
        System.out.println(solution637.averageOfLevelsDfs(root));
        System.out.println(solution637.averageOfLevelsBfs(root));
    }
}
