package solutions.twopointers;

import pojo.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 环形链表 II
 *
 * @author : xianzilei
 * @date : 2020/8/3
 */
public class Solution142 {
    /**
     * 解法一：标记法
     *
     * @param head 1
     * @return pojo.ListNode
     * @author xianzilei
     * @date 2020/8/3 9:29
     **/
    public ListNode detectCycle1(ListNode head) {
        //保存访问过的节点
        Set<ListNode> set = new HashSet<>();
        ListNode node = head;
        //从头节点开始遍历链表
        while (node != null) {
            //如果当前节点已经被遍历过了，则表示链表有环，且当前节点为环的入口，直接返回即可
            if (set.contains(node)) {
                return node;
            }
            //当前节点标记为已访问
            set.add(node);
            //向后遍历
            node = node.next;
        }
        //如果没有环，则返回null
        return null;
    }

    /**
     * 解法二：双指针法
     *
     * @param head 1
     * @return pojo.ListNode
     * @author xianzilei
     * @date 2020/8/3 18:36
     **/
    public ListNode detectCycle2(ListNode head) {
        ListNode slowPointer = head;
        ListNode fastPointer = head;
        //快慢指针遍历
        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
            //当重合直接结束遍历，此时快慢指针在重合点处
            if (slowPointer == fastPointer) {
                break;
            }
        }
        //判断链表是否存在环，如果不存在直接返回null
        if (fastPointer == null || fastPointer.next == null) {
            return null;
        }
        //调整慢指针为头结点
        slowPointer = head;
        //此时快慢指针每次都走一步，知道相遇，相遇点即为环的初始点
        while (slowPointer != fastPointer) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next;
        }
        return slowPointer;
    }

    public static void main(String[] args) {
        Solution142 solution142 = new Solution142();
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
        node15.next = head1;
        System.out.println(solution142.detectCycle1(head1).val);
        System.out.println(solution142.detectCycle2(head1).val);
    }
}
