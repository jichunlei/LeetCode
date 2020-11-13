package solutions.linked_list;

import pojo.ListNode;

/**
 * 奇偶链表
 *
 * @author : xianzilei
 * @date : 2020/11/13 8:14
 */
public class Solution328 {
    /**
     * 解法：双指针法
     *
     * @param head 1
     * @return pojo.ListNode
     * @author xianzilei
     * @date 2020/11/13 8:34
     **/
    public ListNode oddEvenList(ListNode head) {
        //解题思路：定义两个指针，分别指向已经组装好的奇数节点的末端odd和后序未组装到的奇数位置的节点index
        //每次将cur接入到odd的后面即可完成拼接

        //特殊情况的排除
        if (head == null) {
            return null;
        }
        //指向当前最末端的奇数节点位置
        ListNode oddNode = head;
        //指向奇数位置的节点的前序节点（节点的删除时需要用前序节点来操作，避免链表断裂）
        ListNode cur = head.next;
        //当还存在奇数位置的节点
        while (cur != null && cur.next != null) {
            //先保存需要移动的目标节点
            ListNode target = cur.next;
            //先断开目标节点
            cur.next = cur.next.next;
            //目标节点连接到oddNode后面
            target.next = oddNode.next;
            oddNode.next = target;

            //oddNode后移
            oddNode = oddNode.next;
            //cur后移
            cur = cur.next;
        }
        //返回结果
        return head;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        Solution328 solution328 = new Solution328();
        ListNode listNode = solution328.oddEvenList(node1);
        System.out.println();
    }
}
