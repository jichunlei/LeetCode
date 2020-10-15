package solutions.dfs;

import pojo.tree.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 填充每个节点的下一个右侧节点指针 II
 *
 * @author : xianzilei
 * @date : 2020/9/28 8:17
 */
public class Solution117 {
    /**
     * 解法一：层序遍历
     *
     * @param root 1
     * @return pojo.tree.Node
     * @author xianzilei
     * @date 2020/9/28 8:54
     **/
    public Node connect1(Node root) {
        //解题思路：层序遍历，同116题
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node preNode = null;
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (preNode != null) {
                    preNode.next = node;
                }
                preNode = node;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return root;
    }

    /**
     * 解法二：利用已有的next指针
     *
     * @param root 1
     * @return pojo.tree.Node
     * @author xianzilei
     * @date 2020/10/15 8:46
     **/
    public Node connect2(Node root) {
        //解题思路：使用已存在的next指针构建下一层
        if (root == null) {
            return null;
        }
        //当前节点（即已经构建完成next指针的那一层的当前节点）
        Node cur = root;
        //下一层的起始点
        Node start = getStart(root);
        //下一层的前缀节点（初始时刻为空）
        Node pre = null;
        //当下一层结果还存在时
        while (start != null) {
            //如果当前层遍历结束，转向下一层
            if (cur == null) {
                //更新当前节点
                cur = start;
                //更新起始点
                start = getStart(cur);
                //更新前缀节点
                pre = null;
            }
            //如果当前节点存在左右子树，则构建pre->左子树，左子树->右子树
            if (cur.left != null && cur.right != null) {
                //构建pre->左子树
                if (pre != null) {
                    pre.next = cur.left;
                }
                //构建左子树->右子树
                cur.left.next = cur.right;
                //更新前缀节点
                pre = cur.right;
            }
            //如果当前节点只存在左子树，则构建pre->左子树
            else if (cur.left != null) {
                //构建pre->左子树
                if (pre != null) {
                    pre.next = cur.left;
                }
                //更新前缀节点
                pre = cur.left;
            }
            //如果当前节点只存在右子树，则构建pre->右子树
            else if (cur.right != null) {
                //构建pre->右子树
                if (pre != null) {
                    pre.next = cur.right;
                }
                //更新前缀节点
                pre = cur.right;
            }
            //更新当前节点指向下一节点
            cur = cur.next;
        }
        return root;
    }

    //获取起始节点
    private Node getStart(Node node) {
        //找到第一个不为空的节点的第一个不为空的子节点
        while (node != null) {
            if (node.left != null) {
                return node.left;
            } else if (node.right != null) {
                return node.right;
            } else {
                node = node.next;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node7 = new Node(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node7;
        Solution117 solution117 = new Solution117();
        Node node = solution117.connect1(node1);
        System.out.println();
    }
}
