package basic;

import pojo.TreeNode;

import java.util.*;

/**
 * 树相关算法
 *
 * @author : xianzilei
 * @date : 2020/6/7 12:36
 */
public class TreeAlgorithm {

    /**
     * ==========================================前序遍历============================================
     */

    /**
     * 前序遍历（递归）
     *
     * @param root 1
     * @return void
     * @author xianzilei
     * @date 2020/6/7 13:00
     **/
    public static List<Integer> preOrder(TreeNode root) {
        //如果root为空，直接返回空集合
        if (root == null) {
            return new ArrayList<>();
        }
        //结果集
        List<Integer> result = new ArrayList<>();
        //前序遍历（递归）
        preOrderRecursion(root, result);
        //返回结果集
        return result;
    }

    //前序遍历（递归）
    private static void preOrderRecursion(TreeNode node, List<Integer> result) {
        //遍历当前节点
        result.add(node.val);
        //如果左子树存在，递归遍历左子树
        if (node.left != null) {
            preOrderRecursion(node.left, result);
        }
        //如果右子树存在，递归遍历右子树
        if (node.right != null) {
            preOrderRecursion(node.right, result);
        }
    }

    /**
     * 前序遍历（非递归：栈）
     *
     * @param root 1
     * @return void
     * @author xianzilei
     * @date 2020/6/7 13:00
     **/
    public static List<Integer> preOrderNoRecursion(TreeNode root) {
        //如果root为空，直接返回空集合
        if (root == null) {
            return new ArrayList<>();
        }
        //结果集
        List<Integer> result = new ArrayList<>();
        //定义一个栈实现递归
        LinkedList<TreeNode> stack = new LinkedList<>();
        //根节点入栈
        stack.addFirst(root);
        //出栈遍历元素（当栈不为空时）
        while (!stack.isEmpty()) {
            //出栈
            TreeNode node = stack.removeFirst();
            //遍历当前节点
            result.add(node.val);
            //左右子树入栈时需要注意：先入栈右子树，在入栈左子树（因为栈是后入先出的特性）
            //当右子树存在，入栈
            if (node.right != null) {
                stack.addFirst(node.right);
            }
            //当左子树存在，入栈
            if (node.left != null) {
                stack.addFirst(node.left);
            }
        }
        return result;
    }


    /**
     * ==========================================中序遍历============================================
     */

    /**
     * 中序遍历（递归）
     *
     * @param root 1
     * @return java.util.List<java.lang.Integer>
     * @author xianzilei
     * @date 2020/6/7 13:44
     **/
    public static List<Integer> inOrder(TreeNode root) {
        //如果root为空，直接返回空集合
        if (root == null) {
            return new ArrayList<>();
        }
        //结果集
        List<Integer> result = new ArrayList<>();
        //中序遍历（递归）
        inOrderRecursion(root, result);
        //返回结果集
        return result;
    }

    //中序遍历（递归）
    private static void inOrderRecursion(TreeNode node, List<Integer> result) {
        //如果左子树存在，递归遍历左子树
        if (node.left != null) {
            inOrderRecursion(node.left, result);
        }
        //遍历当前节点
        result.add(node.val);
        //如果右子树存在，递归遍历右子树
        if (node.right != null) {
            inOrderRecursion(node.right, result);
        }
    }

    /**
     * 中序遍历（非递归）
     *
     * @param root 1
     * @return java.util.List<java.lang.Integer>
     * @author xianzilei
     * @date 2020/6/7 13:48
     **/
    public static List<Integer> inOrderNoRecursion(TreeNode root) {
        //如果root为空，直接返回空集合
        if (root == null) {
            return new ArrayList<>();
        }
        //结果集
        List<Integer> result = new ArrayList<>();
        //使用栈来替代递归
        LinkedList<TreeNode> stack = new LinkedList<>();
        //保存当前节点
        TreeNode curNode = root;
        while (curNode != null || !stack.isEmpty()) {
            //如果当前节点不为空
            while (curNode != null) {
                //入栈
                stack.addFirst(curNode);
                //curNode指向当前节点的左子树
                curNode = curNode.left;
            }
            //出栈
            TreeNode node = stack.removeFirst();
            //遍历
            result.add(node.val);
            //curNode指向当前节点的右节点
            curNode = node.right;
        }
        //返回结果集
        return result;
    }

    /**
     * ==========================================后序遍历============================================
     */


    /**
     * 后序遍历（递归）
     *
     * @param root 1
     * @return java.util.List<java.lang.Integer>
     * @author xianzilei
     * @date 2020/6/7 14:23
     **/
    public static List<Integer> postOrder(TreeNode root) {
        //如果root为空，直接返回空集合
        if (root == null) {
            return new ArrayList<>();
        }
        //结果集
        List<Integer> result = new ArrayList<>();
        //后序遍历（递归）
        postOrderRecursion(root, result);
        //返回结果集
        return result;
    }

    //后序遍历（递归）
    private static void postOrderRecursion(TreeNode node, List<Integer> result) {
        if (node.left != null) {
            postOrderRecursion(node.left, result);
        }
        if (node.right != null) {
            postOrderRecursion(node.right, result);
        }
        result.add(node.val);
    }

    /**
     * 后续遍历（非递归：栈）
     *
     * @param root 1
     * @return java.util.List<java.lang.Integer>
     * @author xianzilei
     * @date 2020/6/7 14:48
     **/
    public static List<Integer> postOrderNoRecursion(TreeNode root) {
        //如果root为空，直接返回空集合
        if (root == null) {
            return new ArrayList<>();
        }
        //结果集
        List<Integer> result = new ArrayList<>();
        //使用栈来替代递归
        LinkedList<TreeNode> stack = new LinkedList<>();
        //根节点入栈
        stack.addFirst(root);
        while (!stack.isEmpty()) {
            //出栈
            TreeNode node = stack.removeFirst();
            //头插法放入结果集中
            result.add(0, node.val);
            //注意此处入栈顺序：先左节点后右节点
            //如果左节点不为空，入栈
            if (node.left != null) {
                stack.addFirst(node.left);
            }
            //如果右节点不为空，入栈
            if (node.right != null) {
                stack.addFirst(node.right);
            }
        }
        //返回结果集
        return result;
    }


    /**
     * ==========================================层序遍历============================================
     */

    /**
     * 层序遍历（广度优先遍历：BFS）
     *
     * @param root 1
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @author xianzilei
     * @date 2020/6/7 15:15
     **/
    public static List<List<Integer>> levelOrderBfs(TreeNode root) {
        //如果root为空，直接返回空集合
        if (root == null) {
            return new ArrayList<>();
        }
        //结果集
        List<List<Integer>> result = new ArrayList<>();
        //使用队列
        LinkedList<TreeNode> queue = new LinkedList<>();
        //根节点入对
        queue.addLast(root);
        //如果队列不为空
        while (!queue.isEmpty()) {
            //当前队列的大小（每次循环队列存储的元素都是该层的所有节点）
            int size = queue.size();
            //保存每层的节点遍历值
            List<Integer> list = new ArrayList<>();
            //遍历当前层的节点
            for (int i = 0; i < size; i++) {
                //出队
                TreeNode node = queue.removeFirst();
                //遍历
                list.add(node.val);
                //左子树入队
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                //右子树入队
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
            //该层遍历结束，加入到结果集中
            result.add(list);
        }
        //返回结果集
        return result;
    }

    /**
     * 层序遍历（深度优先遍历：DFS）
     *
     * @param root 1
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @author xianzilei
     * @date 2020/6/7 15:16
     **/
    public static List<List<Integer>> levelOrder(TreeNode root) {
        //如果root为空，直接返回空集合
        if (root == null) {
            return new ArrayList<>();
        }
        //结果集
        List<List<Integer>> result = new ArrayList<>();
        //DFS方式递归遍历
        levelOrderDfs(root, 1, result);
        //返回结果集
        return result;
    }

    //深度优先遍历：DFS
    private static void levelOrderDfs(TreeNode node, int level, List<List<Integer>> result) {
        //如果结果集未开辟该层，则新建该层的集合
        if (result.size() < level) {
            result.add(new ArrayList<>());
        }
        //遍历当前节点
        result.get(level - 1).add(node.val);
        //递归遍历左子树
        if (node.left != null) {
            levelOrderDfs(node.left, level + 1, result);
        }
        //递归遍历右子树
        if (node.right != null) {
            levelOrderDfs(node.right, level + 1, result);
        }
    }

    /**
     * =====================================求二叉树的高（最大深度）====================================
     */

    /**
     * 求二叉树最大深度（DFS）
     *
     * @param root 1
     * @return int
     * @author xianzilei
     * @date 2020/6/7 16:52
     **/
    public static int maxDepthDfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepthDfs(root.left);
        int rightDepth = maxDepthDfs(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * 求二叉树最大深度（BFS）
     *
     * @param root 1
     * @return int
     * @author xianzilei
     * @date 2020/6/7 17:00
     **/
    public static int maxDepthBfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        int depth = 0;
        queue.addLast(root);
        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.removeFirst();
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
        }
        return depth;
    }

    /**
     * ===================================从前序与中序遍历序列构造二叉树=================================
     */

    /**
     * 从前序与中序遍历序列构造二叉树（递归）
     *
     * @param preorder 前序遍历序列
     * @param inorder  中序遍历序列
     * @return pojo.TreeNode
     * @author xianzilei
     * @date 2020/6/7 18:20
     **/
    public static TreeNode buildTreeRecursion1(int[] preorder, int[] inorder) {
        //参数合法性校验
        if (preorder == null || preorder.length == 0
                || inorder == null || preorder.length != inorder.length) {
            throw new RuntimeException("参数不合法！");
        }
        //使用map保存中序遍历中数组数值与下标的对应关系
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        //递归构建二叉树
        return buildTreeRecursion1(preorder, 0, preorder.length - 1, inorder, 0,
                inorder.length - 1, inorderMap);
    }

    /**
     * @param preorder   前序遍历序列
     * @param preLeft    前序遍历序列子区间的左边界
     * @param preRight   前序遍历序列子区间的右边界
     * @param inorder    中序遍历序列
     * @param inLeft     中序遍历序列子区间的左边界
     * @param inRight    中序遍历序列子区间的右边界
     * @param inorderMap 中序遍历中数组数值与下标的对应关系
     **/
    private static TreeNode buildTreeRecursion1(int[] preorder, int preLeft, int preRight,
                                                int[] inorder, int inLeft, int inRight,
                                                Map<Integer, Integer> inorderMap) {
        if (preLeft > preRight || inLeft > inRight) {
            return null;
        }
        int rootVal = preorder[preLeft];
        TreeNode root = new TreeNode(rootVal);
        int pIndex = inorderMap.get(rootVal);
        root.left = buildTreeRecursion1(preorder, preLeft + 1, pIndex - inLeft + preLeft,
                inorder, inLeft, pIndex - 1, inorderMap);
        root.right = buildTreeRecursion1(preorder, pIndex - inLeft + preLeft + 1, preRight, inorder,
                pIndex + 1, inRight, inorderMap);
        return root;
    }

    /**
     * ===================================从中序与后序遍历序列构造二叉树=================================
     */

    /**
     * 从中序与后序遍历序列构造二叉树（递归）
     *
     * @param inorder   中序遍历序列
     * @param postorder 后序遍历序列
     * @return pojo.TreeNode
     * @author xianzilei
     * @date 2020/6/7 18:53
     **/
    public static TreeNode buildTreeRecursion2(int[] inorder, int[] postorder) {
        //参数合法性校验
        if (postorder == null || postorder.length == 0
                || inorder == null || inorder.length != postorder.length) {
            throw new RuntimeException("参数不合法！");
        }
        //使用map保存中序遍历中数组数值与下标的对应关系
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        //递归构建二叉树
        return buildTreeRecursion2(postorder, 0, postorder.length - 1, inorder, 0,
                inorder.length - 1, inorderMap);
    }

    /**
     * @param postorder  后序遍历序列
     * @param postLeft   后序遍历序列子区间的左边界
     * @param postRight  后序遍历序列子区间的右边界
     * @param inorder    中序遍历序列
     * @param inLeft     中序遍历序列子区间的左边界
     * @param inRight    中序遍历序列子区间的右边界
     * @param inorderMap 中序遍历中数组数值与下标的对应关系
     **/
    private static TreeNode buildTreeRecursion2(int[] postorder, int postLeft, int postRight,
                                                int[] inorder, int inLeft, int inRight,
                                                Map<Integer, Integer> inorderMap) {
        if (postLeft > postRight || inLeft > inRight) {
            return null;
        }
        int rootVal = postorder[postRight];
        TreeNode root = new TreeNode(rootVal);
        int pIndex = inorderMap.get(rootVal);
        root.left = buildTreeRecursion2(postorder, postLeft, pIndex - inLeft + postLeft - 1,
                inorder, inLeft, pIndex - 1, inorderMap);
        root.right = buildTreeRecursion2(postorder, pIndex - inLeft + postLeft, postRight - 1,
                inorder, pIndex + 1, inRight, inorderMap);
        return root;
    }

    public static void main(String[] args) {
        /**
         *        8
         *     7    13
         *  10         6
         *           3    1
         **/
        TreeNode root = new TreeNode(8);
        TreeNode t1 = new TreeNode(7);
        TreeNode t2 = new TreeNode(13);
        TreeNode t3 = new TreeNode(10);
        TreeNode t4 = new TreeNode(6);
        TreeNode t5 = new TreeNode(3);
        TreeNode t6 = new TreeNode(1);
        root.left = t1;
        root.right = t2;
        t1.left = t3;
        t2.right = t4;
        t4.left = t5;
        t4.right = t6;
        System.out.println("前序遍历（递归）：" + preOrder(root));
        System.out.println("前序遍历（非递归）：" + preOrderNoRecursion(root));
        System.out.println("中序遍历（递归）：" + inOrder(root));
        System.out.println("中序遍历（非递归）：" + inOrderNoRecursion(root));
        System.out.println("后序遍历（递归）：" + postOrder(root));
        System.out.println("后序遍历（递归）：" + postOrderNoRecursion(root));
        System.out.println("层序遍历（BFS）：" + levelOrderBfs(root));
        System.out.println("层序遍历（DFS）：" + levelOrder(root));
        System.out.println("二叉树的高（DFS）：" + maxDepthDfs(root));
        System.out.println("二叉树的高（BFS）：" + maxDepthBfs(root));
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        TreeNode buildTreeRecursion1 = buildTreeRecursion1(preorder, inorder);
        System.out.println("从前序与中序遍历序列构造二叉树（递归）>>前序遍历：" + preOrder(buildTreeRecursion1)
                + " >>中序遍历：" + inOrder(buildTreeRecursion1));
        TreeNode buildTreeRecursion2 = buildTreeRecursion2(inorder, postorder);
        System.out.println("从后序与中序遍历序列构造二叉树（递归）>>后序遍历：" + postOrder(buildTreeRecursion2)
                + " >>中序遍历：" + inOrder(buildTreeRecursion2));
    }
}
