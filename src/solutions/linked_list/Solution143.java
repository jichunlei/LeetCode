package solutions.linked_list;

import pojo.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 重排链表--TODO
 *
 * @author : xianzilei
 * @date : 2020/10/20 8:19
 */
public class Solution143 {

    /**
     * 解法一：线性表解法
     *
     * @param head 1
     * @return void
     * @author xianzilei
     * @date 2020/10/20 9:20
     **/
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode tmp = head;
        while (tmp != null) {
            list.add(tmp);
            tmp = tmp.next;
        }
        int size = list.size();
        if (size == 2) {
            return;
        }
        int left = 0;
        int right = size - 1;
        ListNode pre = null;
        while (left < right) {
            ListNode leftNode = list.get(left);
            ListNode rightNode = list.get(right);
            leftNode.next = rightNode;
            if (pre != null) {
                pre.next = leftNode;
            }
            pre = rightNode;
            left++;
            right--;
        }
        ListNode lastNode = list.get(left);
        if (left == right) {
            pre.next = lastNode;
        }
        lastNode.next = null;
    }

//    public void reorderList2(ListNode head) {
//        if (head == null || head.next == null) {
//            return;
//        }
//        int length = 0;
//        ListNode tmp = head;
//        while (tmp != null) {
//            length++;
//            tmp = tmp.next;
//        }
//        reorderListHelper(head, length);
//    }

//    private ListNode reorderListHelper(ListNode head, int length) {
//        if (length == 1) {
//            head.next = null;
//            return head;
//        }
//        if (length == 2) {
//            ListNode newHead = head.next;
//            head.next = null;
//            newHead.next = head;
//            return newHead;
//        }
//
//    }
}

//    public void reorderList(ListNode head) {
//
//        if (head == null || head.next == null || head.next.next == null) {
//            return;
//        }
//        int len = 0;
//        ListNode h = head;
//        //求出节点数
//        while (h != null) {
//            len++;
//            h = h.next;
//        }
//
//        reorderListHelper(head, len);
//    }
//
//    private ListNode reorderListHelper(ListNode head, int len) {
//        if (len == 1) {
//            ListNode outTail = head.next;
//            head.next = null;
//            return outTail;
//        }
//        if (len == 2) {
//            ListNode outTail = head.next.next;
//            head.next.next = null;
//            return outTail;
//        }
//        //得到对应的尾节点，并且将头结点和尾节点之间的链表通过递归处理
//        ListNode tail = reorderListHelper(head.next, len - 2);
//        ListNode subHead = head.next;//中间链表的头结点
//        head.next = tail;
//        ListNode outTail = tail.next;  //上一层 head 对应的 tail
//        tail.next = subHead;
//        return outTail;
//    }
