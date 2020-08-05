package solutions.dp;

import pojo.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 打家劫舍 III
 *
 * @author : xianzilei
 * @date : 2020/8/5
 */
public class Solution337 {

    /**
     * 解法一：动态规划
     *
     * @param root 1
     * @return int
     * @author xianzilei
     * @date 2020/8/5 15:00
     **/
    public int rob1(TreeNode root) {
        //状态转移方程dp[node]：以当前节点开始向下抢劫可以得到的最大金额数
        //（1）当抢劫当前节点时，则下次抢劫左右子节点的左右子节点
        // 即dp[node]=dp[左右子节点的左右子节点]+node.val
        //（2）当不抢劫当前节点时，则下次抢劫左右子节点
        // 即dp[node]=dp[左右子节点]

        //递归方法定义：从当前节点向下抢劫所得到的最大金额数
        if (root == null) {
            return 0;
        }
        //如果抢劫当前节点
        int result1 = root.val;
        if (root.left != null) {
            result1 += rob1(root.left.left) + rob1(root.left.right);
        }
        if (root.right != null) {
            result1 += rob1(root.right.left) + rob1(root.right.right);
        }
        //如果不抢劫当前节点
        int result2 = rob1(root.left) + rob1(root.right);
        return Math.max(result1, result2);
    }

    /**
     * 解法二：解法一优化（备忘录）
     *
     * @param root 1
     * @return int
     * @author xianzilei
     * @date 2020/8/5 15:20
     **/
    public int rob2(TreeNode root) {
        //定义备忘录
        Map<TreeNode, Integer> memory = new HashMap<>();
        //递归计算
        return dp(root, memory);
    }

    private int dp(TreeNode node, Map<TreeNode, Integer> memory) {
        //如果未空，直接返回0，递归方法的终止条件
        if (node == null) {
            return 0;
        }
        //如果当前节点已经计算过了，直接返回结果即可
        if (memory.containsKey(node)) {
            return memory.get(node);
        }
        //如果抢劫当前节点，则取当前节点+当前节点的孙子节点
        int result1 = node.val;
        //计算当前节点的左子树的孩子节点
        if (node.left != null) {
            result1 += dp(node.left.left, memory) + dp(node.left.right, memory);
        }
        //计算当前节点的右子树的孩子节点
        if (node.right != null) {
            result1 += dp(node.right.left, memory) + dp(node.right.right, memory);
        }
        //如果不抢劫当前节点，则取当前节点的孩子节点
        int result2 = dp(node.left, memory) + dp(node.right, memory);
        int result = Math.max(result1, result2);
        //记入备忘录中
        memory.put(node, result);
        //返回结果
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
        Solution337 solution337 = new Solution337();
        System.out.println(solution337.rob1(root));
        System.out.println(solution337.rob2(root));
    }
}
