package solutions.tree;

import pojo.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 完全二叉树的节点个数
 *
 * @author : xianzilei
 * @date : 2020/11/24 8:22
 */
public class Solution222 {

    /**
     * 解法一：常规法
     *
     * @param root 1
     * @return int
     * @author xianzilei
     * @date 2020/11/24 8:38
     **/
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    /**
     * 解法二：BFS
     *
     * @param root 1
     * @return int
     * @author xianzilei
     * @date 2020/11/24 8:38
     **/
    public int countNodes2(TreeNode root) {
        //获取当前树的高度
        int height = getLevel(root);
        if (height < 2) {
            return height;
        }
        //利用层序遍历法，当遍历到最后一层，计算数量即可
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int curHeight = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (curHeight == height) {
                return (1 << (height - 1)) - 1 + size;
            }
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            curHeight++;
        }
        return 0;
    }

    /**
     * 解法三：递归法
     *
     * @param root 1
     * @return int
     * @author xianzilei
     * @date 2020/11/24 8:47
     **/
    public int countNodes3(TreeNode root) {
        //递归函数：求以root为根节点的树的节点个数
        if (root == null) {
            return 0;
        }
        //左子树高度
        int leftHeight = getLevel(root.left);
        //右子树高度
        int rightHeight = getLevel(root.right);
        //如果左右子树高度相同，说明左子树一定是满二叉树，只需要递归计算右子树即可
        if (leftHeight == rightHeight) {
            return countNodes3(root.right) + (1 << leftHeight);
        }
        //如果不相同，则右子树一定是满二叉树，只需要递归计算左子树即可
        else {
            return countNodes3(root.left) + (1 << rightHeight);
        }
    }

    //计算当前树的高度
    private int getLevel(TreeNode root) {
        TreeNode node = root;
        int height = 0;
        while (node != null) {
            height++;
            node = node.left;
        }
        return height;
    }

    /**
     * 解法四：二分法+位运算
     *
     * @param root 1
     * @return int
     * @author xianzilei
     * @date 2020/11/24 9:02
     **/
    public int countNodes4(TreeNode root) {
        //计算当前树的高度
        int height = getLevel(root);
        if (height < 2) {
            return height;
        }
        //定义二分法的边界
        int low = 1 << (height - 1);
        int high = (1 << height) - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            //如果当前位置的节点存在，则边界右移
            if (exist(root, mid, height)) {
                low = mid;
            }
            //否则边界左移
            else {
                high = mid - 1;
            }
        }
        //返回结果
        return low;
    }

    //判断mid位置的节点是否存在
    private boolean exist(TreeNode root, int mid, int height) {
        int bits = 1 << (height - 1);
        while (root != null && bits > 0) {
            if ((bits & mid) == 0) {
                root = root.left;
            } else {
                root = root.right;
            }
            bits >>= 1;
        }
        return root != null;
    }
}
