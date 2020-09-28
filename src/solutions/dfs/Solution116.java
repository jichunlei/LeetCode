package solutions.dfs;

import pojo.tree.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 填充每个节点的下一个右侧节点指针--TODO
 *
 * @author : xianzilei
 * @date : 2020/9/28 9:13
 */
public class Solution116 {
    /**
     * 解法一：层序遍历
     *
     * @param root 1
     * @return pojo.Node
     * @author xianzilei
     * @date 2020/9/28 9:13
     **/
    public Node connect1(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
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
     * 解法二：
     *
     * @param root 1
     * @return pojo.tree.Node
     * @author xianzilei
     * @date 2020/9/28 9:17
     **/
    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        Node start=root;
        Node cur=start;

        return root;
    }
}
