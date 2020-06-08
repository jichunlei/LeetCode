package basic;

import pojo.ListNode;

/**
 * 递归相关的经典算法
 *
 * @author : xianzilei
 * @date : 2020/6/8 13:38
 */
public class RecursionAlgorithm {
    /**
     * 递归三要素
     * 1、明确你这个函数想要干什么
     * 2、寻找递归结束条件
     * 3、找出函数的等价关系式
     **/

    /**
     * 翻转单链表
     *
     * @param head 1
     * @return pojo.ListNode
     * @author xianzilei
     * @date 2020/6/8 23:00
     **/
    public static ListNode reverseList(ListNode head) {
        //1、首先明确了该方法是翻转单链表，返回头节点
        //2、递归出口是节点为空或者只有一个节点，直接返回
        if (head == null || head.next == null) {
            return head;
        }
        //3、递推公式：翻转单链表=翻转了head.next及之后的节点+调整head和head.next的指向
        //3.1、reverseList(head.next)=head.next及之后的节点
        ListNode newHead = reverseList(head.next);
        //3.2、调整head和head.next的指向
        head.next.next = head;
        head.next = null;
        //返回新头节点
        return newHead;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(6);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        printListNode(reverseList(listNode1));
    }

    //打印链表
    private static void printListNode(ListNode head) {
        if (head == null) {
            System.out.println("null");
        }
        System.out.print(head.val);
        while (head.next != null) {
            System.out.print("->" + head.next.val);
            head = head.next;
        }
    }
}
