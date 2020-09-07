package solutions.dfs;

import pojo.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的所有路径
 *
 * @author : xianzilei
 * @date : 2020/9/4 8:28
 */
public class Solution257 {
    /**
     * 解法一：DFS
     *
     * @param root 1
     * @return java.util.List<java.lang.String>
     * @author xianzilei
     * @date 2020/9/4 8:45
     **/
    public List<String> binaryTreePathsDfs(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        dfs(root, String.valueOf(root.val), result);
        return result;
    }

    private void dfs(TreeNode node, String path, List<String> result) {
        if (node.left == null && node.right == null) {
            result.add(path);
            return;
        }
        if (node.left != null) {
            dfs(node.left, path + "->" + node.left.val, result);
        }
        if (node.right != null) {
            dfs(node.right, path + "->" + node.right.val, result);
        }
    }

    /**
     * 解法二：BFS
     *
     * @param root 1
     * @return java.util.List<java.lang.String>
     * @author xianzilei
     * @date 2020/9/4 8:47
     **/
    public List<String> binaryTreePathsBfs(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<String> pathQueue = new LinkedList<>();
        nodeQueue.add(root);
        pathQueue.add(String.valueOf(root.val));
        while (!nodeQueue.isEmpty()) {
            int size = nodeQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = nodeQueue.poll();
                if (node.left == null && node.right == null) {
                    result.add(pathQueue.poll());
                } else {
                    String path = pathQueue.poll();
                    if (node.left != null) {
                        nodeQueue.add(node.left);
                        pathQueue.offer(path + "->" + node.left.val);
                    }
                    if (node.right != null) {
                        nodeQueue.add(node.right);
                        pathQueue.offer(path + "->" + node.right.val);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //     4
        //    /
        //   2
        //  / \
        // 1   3
        TreeNode root = new TreeNode(4);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        root.left = node1;
        node1.left = node2;
        node1.right = node3;
        Solution257 solution257 = new Solution257();
        System.out.println(solution257.binaryTreePathsDfs(root));
        System.out.println(solution257.binaryTreePathsBfs(root));
    }
}
