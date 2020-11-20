package solutions.linked_list;

import pojo.ListNode;

/**
 * 对链表进行插入排序
 *
 * @author : xianzilei
 * @date : 2020/11/20 8:19
 */
public class Solution147 {
    /**
     * 解法：直接插入排序
     *
     * @param head 1
     * @return pojo.ListNode
     * @author xianzilei
     * @date 2020/11/20 8:35
     **/
    public ListNode insertionSortList(ListNode head) {
        //特殊情况下的排除
        if (head == null || head.next == null) {
            return head;
        }
        //定义排序的开始起点
        //第一个元素默认为已排序好，从第二个元素开始，
        //且start指向当前未排序的元素的前一个元素，目的是方便做链表的移动操作
        ListNode start = head;
        //当还存在未排序的元素时
        while (start.next != null) {
            //当前需要排序的元素
            ListNode cur = start.next;
            //判断是否可以作为已排好序的链表的头结点
            if (cur.val < head.val) {
                //直接将当前元素移动到头结点处
                start.next = cur.next;
                cur.next = head;
                head = cur;
            }
            //判断是否可以作为已排好序的链表的尾结点
            else if (cur.val >= start.val) {
                //无需移动当前节点，只需要start移动一步即可
                start = start.next;
            }
            //需要插入到已排好序的链表中间位置
            else {
                //需要从头遍历查询插入的位置
                ListNode tmp = head;
                while (tmp != cur && tmp.next.val < cur.val) {
                    tmp = tmp.next;
                }
                //插入到指定位置
                start.next = cur.next;
                cur.next = tmp.next;
                tmp.next = cur;
            }
        }
        //返回头结点
        return head;
    }
}
