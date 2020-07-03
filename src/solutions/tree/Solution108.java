package solutions.tree;

import pojo.TreeNode;

/**
 * 将有序数组转换为二叉搜索树
 *
 * @author : xianzilei
 * @date : 2020/7/3 08:13
 */
public class Solution108 {

    public static TreeNode sortedArrayToBST(int[] nums) {
        //特殊情况的判断
        if (nums == null || nums.length == 0) {
            return null;
        }
        //递归创建树
        return buildBST(0, nums.length - 1, nums);
    }

    //指定范围创建二叉树，返回根节点
    private static TreeNode buildBST(int left, int right, int[] nums) {
        if (left > right) {
            return null;
        }
        int mid = left + ((right - left) >> 1);
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildBST(left, mid - 1, nums);
        root.right = buildBST(mid + 1, right, nums);
        return root;
    }

    public static void main(String[] args) {

    }
}
