package questions.stack;

import pojo.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的锯齿形层次遍历
 *
 * @author : xianzilei
 * @date : 2019/12/30 18:36
 */
public class Question103 {

    /**
     * 功能描述: 解法一：BFS
     *
     * @param root
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @author xianzilei
     * @date 2020/5/29 14:58
     **/
    public static List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        //如果root为null，返回空list
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }
        //存放结果
        List<List<Integer>> result = new ArrayList<>();
        //存放节点，队列形式
        Queue<TreeNode> queue = new LinkedList<>();
        //先将根节点入队
        queue.offer(root);
        //标志位，true表示从左向右输出，false表示从右向左输出
        boolean left = true;
        //遍历队列元素
        while (!queue.isEmpty()) {
            //存放每层遍历的值
            LinkedList<Integer> list = new LinkedList<>();
            //队列长度
            int size = queue.size();
            //只遍历当前队列的所有元素，后续入队的元素不遍历
            for (int i = 0; i < size; i++) {
                //出队
                TreeNode node = queue.poll();
                //标志位为true，表示当前层是从左向右输出
                if (left) {
                    //尾插
                    list.addLast(node.val);
                }
                //标志位为false，表示当前层是从右向左输出
                else {
                    //头插
                    list.addFirst(node.val);
                }
                //将左右子节点入队（为空不入队）
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            //翻转标识
            left = !left;
            //将当前层的list加入到结果集中
            result.add(list);
        }
        //返回结果集
        return result;
    }


    public static List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        //如果root为null，返回空list
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }
        //存放结果
        List<List<Integer>> result = new ArrayList<>();
        //dfs方式
        dfs(result, root, 0);
        //返回结果集
        return result;
    }

    private static void dfs(List<List<Integer>> list, TreeNode node, int level) {
        //如果该层不存在，则创建该层
        if (list.size() - 1 < level) {
            list.add(new ArrayList<Integer>());
        }
        //根据level的奇偶性来判断每层的输出顺序
        //偶数：从左到右输出
        //奇数：从右到左输出
        if (level % 2 == 0) {
            list.get(level).add(node.val);
        } else {
            list.get(level).add(0, node.val);
        }

        //左右子节点递归调用
        if (node.left != null) {
            dfs(list, node.left, level + 1);
        }
        if (node.right != null) {
            dfs(list, node.right, level + 1);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode t1 = new TreeNode(2);
        TreeNode t2 = new TreeNode(3);
        TreeNode t3 = new TreeNode(4);
        TreeNode t4 = new TreeNode(5);
        root.left = t1;
        root.right = t2;
        t1.left = t3;
        t2.right = t4;
        System.out.println(zigzagLevelOrder1(root));
        System.out.println(zigzagLevelOrder2(root));
        //[[3], [9, 20], [15, 7], [6, 36]]
    }
}
