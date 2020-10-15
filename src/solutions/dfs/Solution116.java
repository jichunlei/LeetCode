package solutions.dfs;

import pojo.tree.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 填充每个节点的下一个右侧节点指针
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
     * 解法二：使用已有的next指针
     *
     * @param root 1
     * @return pojo.tree.Node
     * @author xianzilei
     * @date 2020/9/28 9:17
     **/
    public Node connect2(Node root) {
        //解题思路：使用上层的已经做好的next引用去处理下一层（根节点出默认已经组装好了next引用）
        if (root == null) {
            return null;
        }
        //每层开始的节点
        Node start = root;
        //当前遍历的节点
        Node cur = start;
        //当存在下一层时
        while (start.left != null) {
            //如果当前遍历的节点不为空
            if (cur != null) {
                //连接当前节点的左右子树
                cur.left.next = cur.right;
                //如果当前节点的next节点存在，则当前节点右子树节点链接到当前节点的next节点的左子树
                //       当前节点    -->   next节点
                //       /    \          /    \
                //    左子树-->右子树 --> 左子树  右子树
                if (cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                //当前节点后移
                cur = cur.next;
            }
            //如果当前节点为空，则说明该层遍历到终点了，进入下一层
            else {
                //进入下一层的开始节点
                start = start.left;
                //更新当前节点
                cur = start;
            }
        }
        //返回结果
        return root;
    }

    /**
     * 解法三：同解法二
     *
     * @param root 1
     * @return pojo.tree.Node
     * @author xianzilei
     * @date 2020/10/15 8:40
     **/
    public Node connect3(Node root) {
        //解题思路：同解法二，只是保存了pre节点，每次连接pre->左子树，左子树->右子树
        if (root == null) {
            return null;
        }
        //每层开始的节点
        Node start = root;
        //当前遍历的节点
        Node cur = start;
        //当前节点的下一层的前序节点
        Node pre = null;
        //当存在下一层时
        while (start.left != null) {
            //如果当前遍历的节点不为空
            if (cur != null) {
                //如果pre不为空，则连接当前节点的左子树上去
                if (pre != null) {
                    pre.next = cur.left;
                }
                //连接当前节点的左右子树
                cur.left.next = cur.right;
                //更新pre
                pre = cur.right;
                //当前节点后移
                cur = cur.next;
            }
            //如果当前节点为空，则说明该层遍历到终点了，进入下一层
            else {
                //进入下一层的开始节点
                start = start.left;
                //更新当前节点
                cur = start;
                //更新pre
                pre = null;
            }
        }
        //返回结果
        return root;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        Solution116 solution116 = new Solution116();
        Node node = solution116.connect2(node1);
        System.out.println();
    }
}
