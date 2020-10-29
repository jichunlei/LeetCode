package solutions.dfs;

import pojo.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 求根到叶子节点数字之和
 *
 * @author : xianzilei
 * @date : 2020/10/29 8:19
 */
public class Solution129 {
    /**
     * 解法一：回溯法
     *
     * @param root 1
     * @return int
     * @author xianzilei
     * @date 2020/10/29 8:37
     **/
    public int sumNumbers(TreeNode root) {
        //根节点为空直接返回0
        if (root == null) {
            return 0;
        }
        //保存每条路径的和
        List<Integer> resultList = new ArrayList<>();
        //保存每条路径上的数字
        List<Integer> numberList = new ArrayList<>();
        //回溯
        backtrack(root, numberList, resultList);
        //加和
        int result = 0;
        for (Integer num : resultList) {
            result += num;
        }
        //返回结果
        return result;
    }

    /**
     * @param node       当前节点
     * @param numberList 路径
     * @param resultList 结果集
     **/
    private void backtrack(TreeNode node, List<Integer> numberList, List<Integer> resultList) {
        //做选择
        numberList.add(node.val);
        //如果到达决策树底层，计算路径和并放入结果集中
        if (node.left == null && node.right == null) {
            int sum = 0;
            for (Integer num : numberList) {
                sum = sum * 10 + num;
            }
            resultList.add(sum);
        }
        //左子树不为空，选择左子树
        if (node.left != null) {
            backtrack(node.left, numberList, resultList);
        }
        //右子树不为空，选择右子树
        if (node.right != null) {
            backtrack(node.right, numberList, resultList);
        }
        //回溯
        numberList.remove(numberList.size() - 1);
    }

    /**
     * 解法二：DFS
     *
     * @param root 1
     * @return int
     * @author xianzilei
     * @date 2020/10/29 8:42
     **/
    public int sumNumbers2(TreeNode root) {
        return dfs(root, 0);
    }

    /**
     * @param node   当前节点
     * @param preSum 上层和
     **/
    private int dfs(TreeNode node, int preSum) {
        //如果node为空，表示当前分支无法构建成叶子节点，直接返回0（注意不是返回preSum）
        if (node == null) {
            return 0;
        }
        //求解当前层的目标和（由于当前层存在节点，则preSum就会被顶上去，因此需要乘10）
        int curSum = preSum * 10 + node.val;
        //如果当前节点是叶子节点，则直接返回结果
        if (node.left == null && node.right == null) {
            return curSum;
        }
        //否则进行双路递归（左右子树求解的结果之和）
        else {
            return dfs(node.left, curSum) + dfs(node.right, curSum);
        }
    }

    /**
     * 解法三：BFS
     *
     * @param root 1
     * @return int
     * @author xianzilei
     * @date 2020/10/29 8:42
     **/
    public int sumNumbers3(TreeNode root) {
        //解题思路：类似层序遍历（每层都乘以相同倍数的10，然后累加求和即可）
        if (root == null) {
            return 0;
        }
        //结果
        int result = 0;
        //节点队列
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        //节点对应当前和
        Queue<Integer> sumQueue = new LinkedList<>();
        //根节点和对应的当前和入对
        nodeQueue.offer(root);
        sumQueue.offer(root.val);
        while (!nodeQueue.isEmpty()) {
            //节点和当前和出队
            TreeNode curNode = nodeQueue.poll();
            Integer curSum = sumQueue.poll();
            //如果当前节点是叶子节点，则当前和加入到结果中
            if (curNode.left == null && curNode.right == null) {
                result += curSum;
            } else {
                //左节点入队，计算左节点对应的和入队
                if (curNode.left != null) {
                    nodeQueue.offer(curNode.left);
                    sumQueue.offer(curSum * 10 + curNode.left.val);
                }
                //右节点入队，计算右节点对应的和入队
                if (curNode.right != null) {
                    nodeQueue.offer(curNode.right);
                    sumQueue.offer(curSum * 10 + curNode.right.val);
                }
            }
        }
        //返回结果
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(0);
        TreeNode node3 = new TreeNode(3);
        root.left = node2;
        root.right = node3;
        Solution129 solution129 = new Solution129();
        System.out.println(solution129.sumNumbers(root));
        System.out.println(solution129.sumNumbers2(root));
        System.out.println(solution129.sumNumbers3(root));
    }
}
