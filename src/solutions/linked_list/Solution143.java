package solutions.linked_list;

import pojo.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 重排链表
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
        //定义线性表
        List<ListNode> list = new ArrayList<>();
        ListNode tmp = head;
        //将链表保存在线性表中
        while (tmp != null) {
            list.add(tmp);
            tmp = tmp.next;
        }
        int size = list.size();
        if (size == 2) {
            return;
        }
        //定义头尾指针
        int left = 0;
        int right = size - 1;
        ListNode pre = null;
        while (left < right) {
            ListNode leftNode = list.get(left);
            ListNode rightNode = list.get(right);
            //连接左右节点
            leftNode.next = rightNode;
            //如果前缀节点不为空，链接
            if (pre != null) {
                pre.next = leftNode;
            }
            //更新前缀节点
            pre = rightNode;
            //移动指针
            left++;
            right--;
        }
        ListNode lastNode = list.get(left);
        //奇数个数的链表需要处理中间节点
        if (left == right) {
            pre.next = lastNode;
        }
        //尾结点处理
        lastNode.next = null;
    }

    /**
     * 解法二：递归法
     *
     * @param head 1
     * @return void
     * @author xianzilei
     * @date 2020/10/21 17:51
     **/
    public void reorderList2(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        int length = 0;
        ListNode tmp = head;
        while (tmp != null) {
            length++;
            tmp = tmp.next;
        }
        reorderListHelper(head, length);
    }

    private ListNode reorderListHelper(ListNode head, int length) {
        //递归方法：处理head开头，长度为length的链表，返回当前长度的后一节点

        //当长度为1，直接返回当前节点的后继节点
        if (length == 1) {
            ListNode outTail = head.next;
            head.next = null;
            return outTail;
        }

        //当长度为2，直接返回第二个节点的后继节点
        if (length == 2) {
            ListNode outTail = head.next.next;
            head.next.next = null;
            return outTail;
        }
        //获取当前长度的尾结点
        ListNode tail = reorderListHelper(head.next, length - 2);
        //中间链表的头节点
        ListNode subHead = head.next;
        //头结点链接到尾结点
        head.next = tail;
        //保存当前长度的尾结点的后继节点
        ListNode outTail = tail.next;
        //尾结点链接到中间链表
        tail.next = subHead;
        //返回当前长度的后继节点
        return outTail;
    }

    public static void main(String[] args) {
        Random random = new Random();
        System.out.println(random.nextInt(5));
    }
}
