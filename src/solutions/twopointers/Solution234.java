package solutions.twopointers;

import pojo.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 回文链表
 *
 * @author : xianzilei
 * @date : 2020/10/23 8:13
 */
public class Solution234 {
    /**
     * 解法一：双指针
     *
     * @param head 1
     * @return boolean
     * @author xianzilei
     * @date 2020/10/23 8:18
     **/
    public boolean isPalindrome(ListNode head) {
        //核心思想：将节点保存到数组中，利用双向索引来不叫回文串

        //特殊情况的排除
        if (head == null || head.next == null) {
            return true;
        }
        //定义数组，并将链表保存到数组中
        List<ListNode> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        //定义双指针
        int left = 0;
        int right = list.size() - 1;
        //比较数组
        while (left < right) {
            if (list.get(left).val != list.get(right).val) {
                return false;
            }
            left++;
            right--;
        }
        //返回结果
        return true;
    }

    /**
     * 解法二：快慢指针+翻转链表
     *
     * @param head 1
     * @return boolean
     * @author xianzilei
     * @date 2020/10/23 8:29
     **/
    public boolean isPalindrome2(ListNode head) {
        //核心思想：利用快慢指针找到链表的后半部分（奇数长度不包含中间节点），
        //将后半部分的链表翻转，然后与前半部分一一比较
        //结束再将后半部分链表翻转

        //特效情况的排除
        if (head == null || head.next == null) {
            return true;
        }
        if (head.next.next == null) {
            return head.val == head.next.val;
        }

        //定义快慢指针
        ListNode fast = head;
        ListNode slow = head;
        //查找链表中间位置
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //后半链表的起点
        ListNode start = slow.next;
        //翻转链表，返回新链表的头节点
        ListNode newHead = reverse(start);
        //比较前半部分链表和后半部分的链表
        while (newHead != null) {
            if (newHead.val != head.val) {
                //恢复后半部分链表
                reverse(start);
                return false;
            }
            newHead = newHead.next;
            head = head.next;
        }
        //恢复后半部分链表
        reverse(start);
        return true;
    }

    //翻转链表
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 解法三：递归
     *
     * @param head 1
     * @return boolean
     * @author xianzilei
     * @date 2020/10/23 9:13
     **/
    public boolean isPalindrome3(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(head);
    }

    private ListNode frontPointer;

    //核心思想就是利用函数栈来保存节点，然后每次从栈中弹出节点与开头节点比较
    private boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode != null) {
            if (!recursivelyCheck(currentNode.next)) {
                return false;
            }
            if (currentNode.val != frontPointer.val) {
                return false;
            }
            frontPointer = frontPointer.next;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        Solution234 solution234 = new Solution234();
        System.out.println(solution234.isPalindrome2(head));
    }
}
