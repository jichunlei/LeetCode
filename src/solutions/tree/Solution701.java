package solutions.tree;

import pojo.TreeNode;

/**
 * 二叉搜索树中的插入操作
 *
 * @author : xianzilei
 * @date : 2020/9/30 8:20
 */
public class Solution701 {
    /**
     * 解法一：迭代法
     *
     * @param root 1
     * @param val  2
     * @return pojo.TreeNode
     * @author xianzilei
     * @date 2020/9/30 8:42
     **/
    public TreeNode insertIntoBST1(TreeNode root, int val) {
        //先创建好新的节点
        TreeNode target = new TreeNode(val);
        //如果树为空，则返回新节点
        if (root == null) {
            return target;
        }
        //遍历根节点
        TreeNode cur = root;
        while (true) {
            //如果当前节点的值大于目标值，则节点需要插入到左子树上
            if (cur.val > val) {
                //如果左子树为空，则直接将新节点链接到当前节点的左节点上
                if (cur.left == null) {
                    cur.left = target;
                    break;
                }
                //否则进入左子树进一步判断
                else {
                    cur = cur.left;
                }
            }
            //如果当前节点的值小于于目标值，则节点需要插入到右子树上
            else {
                //如果右子树为空，则直接将新节点链接到当前节点的右节点上
                if (cur.right == null) {
                    cur.right = target;
                    break;
                }
                //否则进入右子树进一步判断
                else {
                    cur = cur.right;
                }
            }
        }
        //返回结果
        return root;
    }

    /**
     * 解法二：递归写法
     *
     * @param root 1
     * @param val  2
     * @return pojo.TreeNode
     * @author xianzilei
     * @date 2020/9/30 8:44
     **/
    public TreeNode insertIntoBST(TreeNode root, int val) {
        //递归函数定义：将当前值插入到树中，并返回插入后的树的根节点
        //递归终止条件，当前树为空，直接返回当前值的新节点
        if (root == null) {
            return new TreeNode(val);
        }
        //插入到左子树上
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        }
        //插入到右子树上
        else {
            root.right = insertIntoBST(root.right, val);
        }
        //返回根节点
        return root;
    }

}
