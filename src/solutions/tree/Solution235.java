package solutions.tree;

import pojo.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜索树的最近公共祖先
 *
 * @author : xianzilei
 * @date : 2020/9/27 8:20
 */
public class Solution235 {
    /**
     * 解法一：两次遍历
     *
     * @param root 1
     * @param p    2
     * @param q    3
     * @return pojo.TreeNode
     * @author xianzilei
     * @date 2020/9/27 8:53
     **/
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        //获取根节点到节点p的路径
        List<TreeNode> path1 = getPath(root, p);
        //获取根节点到节点q的路径
        List<TreeNode> path2 = getPath(root, q);
        TreeNode result = null;
        //找到两个路径的分叉点即为本题结果
        for (int i = 0; i < path1.size() && i < path2.size(); i++) {
            if (path1.get(i) == path2.get(i)) {
                result = path1.get(i);
            } else {
                break;
            }
        }
        return result;
    }

    //获取根节点到目标节点的路径
    private List<TreeNode> getPath(TreeNode root, TreeNode targetNode) {
        List<TreeNode> path = new ArrayList<>();
        TreeNode node = root;
        while (node != targetNode) {
            path.add(node);
            //根据二叉搜索树的特点进行路径的选择
            if (node.val > targetNode.val) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        path.add(node);
        return path;
    }

    /**
     * 解法二：一次遍历
     *
     * @param root 1
     * @param p    2
     * @param q    3
     * @return pojo.TreeNode
     * @author xianzilei
     * @date 2020/9/27 9:18
     **/
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode result = root;
        //直接遍历二叉树，找二者的路径分叉点，或者其中一个节点是另一个节点的祖先
        while (true) {
            if (result.val > p.val && result.val > q.val) {
                result = result.left;
            } else if (result.val < p.val && result.val < q.val) {
                result = result.right;
            } else {
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(8);
        TreeNode node4 = new TreeNode(0);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(9);
        TreeNode node8 = new TreeNode(3);
        TreeNode node9 = new TreeNode(5);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node5.left = node8;
        node5.right = node9;
        Solution235 solution235 = new Solution235();
        TreeNode result = solution235.lowestCommonAncestor1(root, node2, node5);
        System.out.println(result.val);
    }
}
