package solutions.dfs;

import pojo.tree.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 填充每个节点的下一个右侧节点指针 II--TODO
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

    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        Node preStart = root;
        Node preCur = preStart;
        Node cur = null;
        while (preCur != null) {
            if (preCur.left != null && preCur.right != null) {
                cur = preCur.left;
                cur.next = preCur.right;
            } else if (preCur.right != null) {
                cur = preCur.right;
            } else if (preCur.left != null) {
                cur = preCur.left;
            } else {
                break;
            }
            preCur = preCur.next;
        }

        return root;
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
