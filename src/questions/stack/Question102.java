package questions.stack;

import pojo.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的层次遍历
 *
 * @author : xianzilei
 * @date : 2020/5/29 15:49
 */
public class Question102 {
    /**
     * 功能描述: 解法一：BFS
     *
     * @param root
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @author xianzilei
     * @date 2020/5/29 15:51
     **/
    public static List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            LinkedList<Integer> list = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    list.add(node.val);
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
            }
            if (!list.isEmpty()) {
                result.add(list);
            }
        }
        return result;
    }

    /**
     * 功能描述: 解法二：DFS
     *
     * @param root
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @author xianzilei
     * @date 2020/5/29 15:51
     **/
    public static List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, root, 0);
        return result;
    }

    private static void dfs(List<List<Integer>> result, TreeNode node, int level) {
        if (result.size() - 1 < level) {
            result.add(new ArrayList<Integer>());
        }
        result.get(level).add(node.val);
        if (node.left != null) {
            dfs(result, node.left, level + 1);
        }
        if (node.right != null) {
            dfs(result, node.right, level + 1);
        }
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode t1 = new TreeNode(9);
        TreeNode t2 = new TreeNode(20);
        TreeNode t3 = new TreeNode(15);
        TreeNode t4 = new TreeNode(7);
        TreeNode t5 = new TreeNode(36);
        TreeNode t6 = new TreeNode(6);
        root.left = t1;
        root.right = t2;
        t2.left = t3;
        t2.right = t4;
        t3.left = t6;
        t4.right = t5;
        System.out.println(levelOrder1(root));
        System.out.println(levelOrder2(root));
    }
}
