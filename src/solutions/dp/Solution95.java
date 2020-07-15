package solutions.dp;

import pojo.Codec;
import pojo.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 不同的二叉搜索树 II
 *
 * @author : xianzilei
 * @date : 2020/7/15 10:15
 */
public class Solution95 {
    /**
     * 解法一：递归写法
     *
     * @param n 1
     * @return java.util.List<pojo.TreeNode>
     * @author xianzilei
     * @date 2020/7/15 14:16
     **/
    public static List<TreeNode> generateTrees1(int n) {
        //特殊情况下的排除
        if (n == 0) {
            return new ArrayList<>();
        }
        //递归求解
        return buildTree(1, n);
    }

    //递归方法定义：返回从start到end区间（闭区间）的多种组合方式的根节点集合
    private static List<TreeNode> buildTree(int start, int end) {
        //初始化结果集合
        List<TreeNode> nodes = new ArrayList<>();
        //如果区间没有值，直接返回null节点
        if (start > end) {
            nodes.add(null);
            return nodes;
        }
        //如果只有一个元素，则直接返回以该元素作为的根节点
        if (start == end) {
            nodes.add(new TreeNode(start));
            return nodes;
        }
        //循环区间内的每个数据，尝试将每个数据作为根节点
        for (int i = start; i <= end; i++) {
            //获取以i前面元素的组成的二叉树的根节点集合
            List<TreeNode> leftNodes = buildTree(start, i - 1);
            //获取以i后面元素的组成的二叉树的根节点集合
            List<TreeNode> rightNodes = buildTree(i + 1, end);
            //组合两个根节点集合
            for (TreeNode leftNode : leftNodes) {
                for (TreeNode rightNode : rightNodes) {
                    //创建根节点
                    TreeNode root = new TreeNode(i);
                    //组装左子树
                    root.left = leftNode;
                    //组装右子树
                    root.right = rightNode;
                    //添加该种情况到结果集合中
                    nodes.add(root);
                }
            }
        }
        //返回结果集合
        return nodes;
    }

    /**
     * 解法二：动态规划（自底向上推算）
     *
     * @param n 1
     * @return java.util.List<pojo.TreeNode>
     * @author xianzilei
     * @date 2020/7/15 14:52
     **/
    public static List<TreeNode> generateTrees2(int n) {
        //解题思路：
        //我们可以从最初一层一层推算到n
        //比如我们知道了1~i-1的树列表dp(i-1)，要得到第i层的结果，只需遍历dp(i-1)，对每种结果插入节点i，即可得到dp(i)
        //接下来的问题就是插入节点i的问题了，因为i是大于当前树的任何一个节点的，所以i的插入位置有两种
        //第一种插入位置：i当作根节点
        //第二种插入位置：i当作右节点（可以是根节点的右孩子，根节点的右孩子的右孩子，...）
        //所以将每种插入方式产出的新树放入到结果列表中，即可得到dp(i)
        //由于dp(i)的结果只与dp(i-1)有关，所以直接进行状态压缩，使用list保存上一层的结果

        //特殊情况的排除
        if (n == 0) {
            return new ArrayList<>();
        }
        //pre表示上一层不同二叉树集合列表
        List<TreeNode> pre = new ArrayList<>();
        //初始化第一层
        pre.add(new TreeNode(1));
        //从第二层开始，一层一层进行计算
        for (int i = 2; i <= n; i++) {
            //初始化集合保存当前层的树集合
            List<TreeNode> cur = new ArrayList<>();
            //遍历上层结果
            for (TreeNode root : pre) {
                //第一种插入方式：作为根节点
                TreeNode insert1 = new TreeNode(i);
                insert1.left = root;
                cur.add(insert1);
                //第二种插入方式：作为右孩子
                TreeNode tmp = root;
                //这里注意一下，需要预先计算出插入位置的节点所在的高度，方便后面根据高度获取插入的位置
                int high = 0;
                while (tmp != null) {
                    high++;
                    tmp = tmp.right;
                }
                //高度多少，就有作为右孩子的方案
                for (int j = 1; j <= high; j++) {
                    //复制一份当前树结构
                    TreeNode copyTree = copyTree(root);
                    //定义临时指针，用来定位插入新节点的位置
                    TreeNode tmp1 = copyTree;
                    //找到插入点
                    int cnt = j;
                    while (cnt > 1) {
                        tmp1 = tmp1.right;
                        cnt--;
                    }
                    //插入新节点，原该处的子树作为新节点的左子树
                    TreeNode insert2 = new TreeNode(i);
                    insert2.left = tmp1.right;
                    tmp1.right = insert2;
                    //当前树添加到结果集中
                    cur.add(copyTree);
                }
            }
            //赋值给pre作为下一层的插入基准
            pre = cur;
        }
        //返回结果
        return pre;
    }

    //复制treeNode节点
    private static TreeNode copyTree(TreeNode treeNode) {
        //如果节点为空，直接返回空
        if (treeNode == null) {
            return null;
        }
        //复制根节点
        TreeNode root = new TreeNode(treeNode.val);
        //递归复制左子树
        root.left = copyTree(treeNode.left);
        //递归复制右子树
        root.right = copyTree(treeNode.right);
        //返回结果
        return root;
    }

    public static void main(String[] args) {
        Codec codec = new Codec();
        for (TreeNode treeNode : generateTrees1(3)) {
            System.out.println(codec.serialize(treeNode));
        }
        for (TreeNode treeNode : generateTrees2(3)) {
            System.out.println(codec.serialize(treeNode));
        }
    }

}
