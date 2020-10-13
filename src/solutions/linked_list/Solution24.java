package solutions.linked_list;

import pojo.ListNode;

/**
 * 两两交换链表中的节点
 *
 * @author : xianzilei
 * @date : 2020/10/13 8:20
 */
public class Solution24 {

    /**
     * 解法一：递归法
     *
     * @param head 1
     * @return pojo.ListNode
     * @author xianzilei
     * @date 2020/10/13 8:43
     **/
    public ListNode swapPairs(ListNode head) {
        //递归函数定义：以head为头结点的链表翻转，返回新的头节点
        //递归函数出口：当前头结点为空或者当前链表长度为1
        if (head == null || head.next == null) {
            return head;
        }
        //保存新的头节点
        ListNode newHead = head.next;
        //递归求head的next指向
        head.next = swapPairs(newHead.next);
        //翻转当前链表
        newHead.next = head;
        return newHead;
    }

    /**
     * 解法二：迭代法
     *
     * @param head 1
     * @return pojo.ListNode
     * @author xianzilei
     * @date 2020/10/13 8:33
     **/
    public ListNode swapPairs2(ListNode head) {
        //特殊情况的排除
        if (head == null || head.next == null) {
            return head;
        }
        //预先保存新链表的根节点
        ListNode result = head.next;
        //每两个节点的前序节点
        ListNode pre = null;
        //每两个节点的开始节点
        ListNode cur = head;
        //下一批两个节点的开始节点
        ListNode next = null;
        //当存在下一批节点时
        while (cur != null && cur.next != null) {
            //预先保存下一批节点的起始点
            next = cur.next.next;
            //如果前序节点不为空，则将翻转后的节点接入
            if (pre != null) {
                pre.next = cur.next;
            }
            //翻转
            cur.next.next = cur;
            //消环
            cur.next = null;
            //往后移动
            pre = cur;
            cur = next;
        }
        //当下一批节点不满2个时，不翻转，直接挂载
        if (cur != null && pre != null) {
            pre.next = cur;
        }
        //返回结果
        return result;
    }
}
