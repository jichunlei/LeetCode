package solutions.tree;

import pojo.ListNode;
import pojo.TreeNode;
import solutions.array.Solution1;

/**
 * 有序链表转换二叉搜索树
 *
 * @author : xianzilei
 * @date : 2020/8/18
 */
public class Solution109 {

    /**
     * 解法一：分治法
     *
     * @param head 1
     * @return pojo.TreeNode
     * @author xianzilei
     * @date 2020/8/18 8:16
     **/
    public TreeNode sortedListToBST1(ListNode head) {
        return buildBST(head, null);
    }

    //递归方法定义：将区间[left,right)的链表转化为平衡二叉树，并返回根节点（注意区间是左闭右开）
    private TreeNode buildBST(ListNode left, ListNode right) {
        //递归终止条件：区间没有任何节点
        if (left == right) {
            return null;
        }
        //获取中间节点
        ListNode midNode = getMidNode(left, right);
        //中间节点作为根节点
        TreeNode root = new TreeNode(midNode.val);
        //递归构建左子树
        root.left = buildBST(left, midNode);
        //递归构建右子树
        root.right = buildBST(midNode.next, right);
        //返回构建完成的根节点
        return root;
    }

    //获取区间[left,right)的中间节点
    private ListNode getMidNode(ListNode left, ListNode right) {
        //使用快慢指针的思想
        ListNode fast = left;
        ListNode slower = left;
        while (fast != right && fast.next != right) {
            fast = fast.next.next;
            slower = slower.next;
        }
        return slower;
    }

    ListNode globalHead;

    /**
     * 解法二：中序遍历解法
     *
     * @param head 1
     * @return pojo.TreeNode
     * @author xianzilei
     * @date 2020/8/18 8:58
     **/
    public TreeNode sortedListToBST2(ListNode head) {
        //考虑到中序遍历即为链表的顺序

        //记录当前遍历到的节点位置
        globalHead = head;
        //求链表的长度
        int length = getLength(head);
        return buildBST(0, length - 1);
    }

    //获取链表的长度
    private int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    //将区间[left,right]内的链表节点转化为平衡二叉树，并返回根节点
    private TreeNode buildBST(int left, int right) {
        //递归终止条件：区间没有任何节点
        if (left > right) {
            return null;
        }
        //计算根节点的位置（中间靠右）
        int mid = left + (right - left + 1) / 2;

        //预先定义出根节点，然后根据中序遍历的方式进行构建
        TreeNode root = new TreeNode();
        //构建左子树
        root.left = buildBST(left, mid - 1);
        //此时的位置即为当前根节点的位置
        root.val = globalHead.val;
        //当前节点往后
        globalHead = globalHead.next;
        //构建有子树
        root.right = buildBST(mid + 1, right);
        //返回构建完成的根节点
        return root;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        Solution109 solution109 = new Solution109();
        TreeNode treeNode = solution109.sortedListToBST2(listNode1);
        System.out.println();
    }
}
