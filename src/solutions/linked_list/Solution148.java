package solutions.linked_list;

import pojo.ListNode;

/**
 * 排序链表--TODO
 *
 * @author : xianzilei
 * @date : 2020/11/23 9:04
 */
public class Solution148 {
    /**
     * 解法一：直接插入排序（同题目147，这里不再注释说明）
     *
     * @param head 1
     * @return pojo.ListNode
     * @author xianzilei
     * @date 2020/11/23 9:05
     **/
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode start = head;
        ListNode cur;
        while (start.next != null) {
            cur = start.next;
            if (start.val <= cur.val) {
                start = start.next;
            } else if (cur.val < head.val) {
                start.next = cur.next;
                cur.next = head;
                head = cur;
            } else {
                ListNode tmp = head;
                while (tmp.next != start && tmp.next.val <= cur.val) {
                    tmp = tmp.next;
                }
                start.next = cur.next;
                cur.next = tmp.next;
                tmp.next = cur;
            }
        }
        return head;
    }


    public static void main(String[] args) {
        ListNode node1 = new ListNode(-1);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(0);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        Solution148 solution148 = new Solution148();
        ListNode listNode = solution148.sortList(node1);
    }
//    [-1,5,3,4,0]
}
