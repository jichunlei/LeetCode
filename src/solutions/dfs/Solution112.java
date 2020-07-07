package solutions.dfs;

import pojo.TreeNode;

import java.util.LinkedList;

/**
 * 路径总和
 *
 * @author : xianzilei
 * @date : 2020/7/7 07:50
 */
public class Solution112 {


    /**
     * 解法一：递归（DFS）
     *
     * @param root 1
     * @param sum  2
     * @return boolean
     * @author xianzilei
     * @date 2020/7/7 8:06
     **/
    public static boolean hasPathSum1(TreeNode root, int sum) {
        return helper(root, sum);
    }

    //递归方法的作用：从当前节点开始到叶子节点，是否存在和为sum的路径
    //递归终止条件：当前节点为空或当前节点就是叶子节点，可以直接判断返回
    //等价关系式：
    //--当前节点左节点到叶子节点是否存在是否存在和为sum-node.val的路径
    //--当前节点右节点到叶子节点是否存在是否存在和为sum-node.val的路径
    private static boolean helper(TreeNode node, int sum) {
        //当前节点为空，直接返回false
        if (node == null) {
            return false;
        }
        //当前节点就是叶子节点，判断当前节点的值是否为指定值
        if (node.left == null && node.right == null) {
            return node.val == sum;
        }
        //左右子树是否满足条件
        return helper(node.left, sum - node.val) || helper(node.right, sum - node.val);
    }

    /**
     * 解法二：BFS（类似二叉树的层次遍历）
     *
     * @param root 1
     * @param sum  2
     * @return boolean
     * @author xianzilei
     * @date 2020/7/7 8:40
     **/
    public static boolean hasPathSum2(TreeNode root, int sum) {
        //如果根节点为空，直接返回
        if (root == null) {
            return false;
        }
        //保存节点（层序遍历）
        LinkedList<TreeNode> nodes = new LinkedList<>();
        //保持根节点到上面该位置的路径和（与上面的队列一一对应）
        LinkedList<Integer> sums = new LinkedList<>();
        //根节点入队
        nodes.addFirst(root);
        //根节点到当前节点的和入队
        sums.addFirst(root.val);
        //当节点队列不为空时
        while (!nodes.isEmpty()) {
            //节点出队
            TreeNode node = nodes.removeFirst();
            //当前节点对应的路径和出队
            Integer tmp = sums.removeFirst();
            //如果当前节点就是叶子节点，则可以直接判断和与sum的关系，如果相等则直接返回true，否则继续出队判断其余节点的情况
            if (node.left == null & node.right == null) {
                if (tmp == sum) {
                    return true;
                }
                //因为当前节点左右子树都为空，没必要进行下面的判断，直接结束当层循环即可
                continue;
            }
            //能够执行到这说明当前节点不是叶子节点，需要继续向下计算路径和
            //如果左子树不为空，则左子树及其路径和入队
            if (node.left != null) {
                nodes.addFirst(node.left);
                sums.addFirst(node.left.val + tmp);
            }
            //如果右子树不为空，则右子树及其路径和入队
            if (node.right != null) {
                nodes.addFirst(node.right);
                sums.addFirst(node.right.val + tmp);
            }
        }
        //如果上面出队操作没有返回结果，则说明不存在路径和为sum的情况，直接返回false
        return false;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(8);
        TreeNode node3 = new TreeNode(11);
        TreeNode node4 = new TreeNode(13);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(2);
        TreeNode node8 = new TreeNode(1);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node5.right = node8;
        System.out.println(hasPathSum1(root, 22));
        System.out.println(hasPathSum2(root, 22));
    }
}
