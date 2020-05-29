package questions.stack;

import pojo.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的锯齿形层次遍历
 *
 * @author : xianzilei
 * @date : 2019/12/30 18:36
 */
public class Question103 {

    /**
     * 功能描述: 解法一：BFS
     *
     * @param root
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @author xianzilei
     * @date 2020/5/29 14:58
     **/
    public static List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean left = false;
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
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
        System.out.println(zigzagLevelOrder1(root));
        //[[3], [9, 20], [15, 7], [6, 36]]
    }
}
