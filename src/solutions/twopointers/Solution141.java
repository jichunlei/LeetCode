package solutions.twopointers;

import pojo.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 环形链表
 *
 * @author : xianzilei
 * @date : 2020/7/29
 */
public class Solution141 {
    /**
     * 解法一：标记法
     *
     * @param head 1
     * @return boolean
     * @author xianzilei
     * @date 2020/7/29 17:57
     **/
    public static boolean hasCycle1(ListNode head) {
        //定义set保存已经访问过的节点
        Set<ListNode> set = new HashSet<>();
        ListNode node = head;
        //开始遍历链表
        while (node != null) {
            //如果发现链表已经被访问过，则表示存在环，直接返回true
            if (set.contains(node)) {
                return true;
            }
            //节点标记为已访问
            set.add(node);
            //继续访问下一节点
            node = node.next;
        }
        //如果节点遍历完成，直接返回false
        return false;
    }

    /**
     * 解法二：快慢指针法
     *
     * @param head 1
     * @return boolean
     * @author xianzilei
     * @date 2020/7/29 17:40
     **/
    public static boolean hasCycle2(ListNode head) {
        //特殊情况的排除
        if (head == null) {
            return false;
        }
        //定义慢、快指针，分别指向头节点和头结点的下一节点
        ListNode slowPointer = head;
        ListNode fastPointer = head.next;
        //当快慢指针不重合时
        while (slowPointer != fastPointer) {
            //如果快指针到达或即将到达链表结尾，则直接返回false，该情况一定没有环
            if (fastPointer == null || fastPointer.next == null) {
                return false;
            }
            //快指针每次走2步
            fastPointer = fastPointer.next.next;
            //慢指针每次走1步
            slowPointer = slowPointer.next;
        }
        //当快慢指针重合则表示存在环
        return true;
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(0);
        ListNode node11 = new ListNode(1);
        ListNode node12 = new ListNode(2);
        ListNode node13 = new ListNode(3);
        ListNode node14 = new ListNode(4);
        ListNode node15 = new ListNode(5);
        head1.next = node11;
        node11.next = node12;
        node12.next = node13;
        node13.next = node14;
        node14.next = node15;

        ListNode head2 = new ListNode(0);
        ListNode node21 = new ListNode(1);
        ListNode node22 = new ListNode(2);
        ListNode node23 = new ListNode(3);
        ListNode node24 = new ListNode(4);
        ListNode node25 = new ListNode(5);
        head2.next = node21;
        node21.next = node22;
        node22.next = node23;
        node23.next = node24;
        node24.next = node25;
        node25.next = node23;

        System.out.println(hasCycle1(head1));
        System.out.println(hasCycle1(head2));
        System.out.println(hasCycle2(head1));
        System.out.println(hasCycle2(head2));
    }
}
