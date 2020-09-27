package solutions.dfs;

import pojo.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 路径总和 II--TODO
 *
 * @author : xianzilei
 * @date : 2020/9/27 9:25
 */
public class Solution113 {
    /**
     * 解法一：DFS
     *
     * @param root 1
     * @param sum  2
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @author xianzilei
     * @date 2020/9/27 18:24
     **/
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tmpPath = new ArrayList<>();
        dfs(root, 0, tmpPath, sum, result);
        return result;
    }

    //回溯
    private void dfs(TreeNode node, int curSum, List<Integer> tmpPath, int targetSum, List<List<Integer>> result) {
        if (node == null) {
            return;
        }
        //节点添加到路径中
        tmpPath.add(node.val);
        //计算当前和
        curSum += node.val;
        //如果到达叶子节点处，且当前和等于目标和
        if (node.left == null && node.right == null & curSum == targetSum) {
            result.add(new ArrayList<>(tmpPath));
        }
        //遍历左子树
        dfs(node.left, curSum, tmpPath, targetSum, result);
        //遍历右子树
        dfs(node.right, curSum, tmpPath, targetSum, result);
        //回溯
        tmpPath.remove(tmpPath.size() - 1);
    }

    public static void main(String[] args) {
        //              5
        //             / \
        //            4   8
        //           /   / \
        //          11  13  4
        //         /  \    / \
        //        7    2  5   1
        TreeNode root = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(8);
        TreeNode node4 = new TreeNode(11);
        TreeNode node5 = new TreeNode(13);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(2);
        TreeNode node9 = new TreeNode(5);
        TreeNode node10 = new TreeNode(1);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node3.left = node5;
        node3.right = node6;
        node4.left = node7;
        node4.right = node8;
        node6.left = node9;
        node6.right = node10;
        Solution113 solution113 = new Solution113();
        System.out.println(solution113.pathSum(root, 22));
        //[5,4,11,2],[5,8,4,5]
    }

}
