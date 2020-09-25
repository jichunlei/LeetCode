package solutions.tree;

import pojo.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 从中序与后序遍历序列构造二叉树
 *
 * @author : xianzilei
 * @date : 2020/9/25 8:21
 */
public class Solution106 {
    /**
     * 解法：迭代法
     *
     * @param inorder   1
     * @param postorder 2
     * @return pojo.TreeNode
     * @author xianzilei
     * @date 2020/9/25 9:10
     **/
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int length = inorder.length;
        //使用哈希表保存中序序列的值和索引，目的方便查找根节点的位置所在
        Map<Integer, Integer> inorderMap = new HashMap<>(length);
        for (int i = 0; i < length; i++) {
            inorderMap.put(inorder[i], i);
        }
        //递归构建树
        return build(inorder, 0, length - 1, postorder, 0, length - 1, inorderMap);
    }

    /**
     * @param inorder    中序序列
     * @param iLeft      中序序列左边界
     * @param iRight     中序序列右边界
     * @param postorder  后序序列
     * @param pLeft      后序序列左边界
     * @param pRight     后序序列右边界
     * @param inorderMap 中序序列哈希表
     **/
    private TreeNode build(int[] inorder, int iLeft, int iRight, int[] postorder, int pLeft, int pRight, Map<Integer, Integer> inorderMap) {
        //如果区间为空，直接返回null
        if (iLeft > iRight || pLeft > pRight) {
            return null;
        }
        //构建根节点
        int rootVal = postorder[pRight];
        TreeNode root = new TreeNode(rootVal);
        int rootIndex = inorderMap.get(rootVal);
        //构建左子树
        root.left = build(inorder, iLeft, rootIndex - 1, postorder, pLeft, pLeft - iLeft + rootIndex - 1, inorderMap);
        //构建右子树
        root.right = build(inorder, rootIndex + 1, iRight, postorder, pLeft - iLeft + rootIndex, pRight - 1, inorderMap);
        return root;
    }

}
