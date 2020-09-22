package solutions.dfs;

import pojo.TreeNode;

/**
 * 监控二叉树
 *
 * @author : xianzilei
 * @date : 2020/9/22 8:37
 */
public class Solution968 {

    int result = 0;

    /**
     * 解法：后序遍历+状态转移
     *
     * @param root 1
     * @return int
     * @author xianzilei
     * @date 2020/9/22 9:04
     **/
    public int minCameraCover(TreeNode root) {
        //每个节点的共有三种状态
        //0-节点待覆盖
        //1-节点被覆盖
        //2-节点有摄像头
        //这个题目本质是一种贪心算法，始终从子节点来开始放置摄像头，且为了摄像头的最大覆盖，叶子节点始终不放摄像头
        //因此本题考虑使用后续遍历来逐个放置摄像头，即从下往上逐个放摄像头
        if (dfs(root) == 0) {
            result++;
        }
        return result;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 1;
        }
        int left = dfs(node.left);
        int right = dfs(node.right);
        //左右子节点的状态有：00,01,10,02,20,11,12,21,22,
        //当左右节点状态为：00,01,10,02,20时（即存在节点状态为0-未覆盖），需要根节点放置摄像头
        //当左右节点状态为：11时（即左右节点都被覆盖了），为了摄像头的最大利用，当前节点需交给上层节点的摄像头来覆盖，即设置为0-待覆盖
        //当左右节点状态为：12,21,22时，此时很明显，当前节点的状态为1-被覆盖
        if (left == 0 || right == 0) {
            result++;
            return 2;
        }
        if (left == 1 && right == 1) {
            return 0;
        }
        if (left == 2 || right == 2) {
            return 1;
        }
        //上面的if已经枚举了所有的情况，此处return永远不会走到，return任意值即可
        return 0;
    }
}
