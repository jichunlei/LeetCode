package questions.linked_list;

import questions.pojo.ListNode;

/**
 * @Auther: xianzilei
 * @Date: 2019/10/7 07:57
 * @Description: 两数相加
 */
public class Question2 {
    /**
     * 常规做法
     *
     * @param l1 1
     * @param l2 2
     * @return: questions.ListNode
     * @auther: xianzilei
     * @date: 2019/10/7 8:05
     **/
    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        //初始化头结点
        ListNode initHeadNode = new ListNode(0);
        //初始化进位数
        int carry = 0;
        ListNode p1 = l1, p2 = l2, temp = initHeadNode;
        while (p1 != null || p2 != null) {
            int val1 = p1 == null ? 0 : p1.val;
            int val2 = p2 == null ? 0 : p2.val;
            int result = val1 + val2 + carry;
            //如果和大于或等于10则代表有进位
            if (result >= 10) {
                carry = 1;
                result = result - 10;
            } else {
                carry = 0;
            }
            //新建节点
            temp.next = new ListNode(result);
            temp = temp.next;
            if (p1 != null) {
                p1 = p1.next;
            }
            if (p2 != null) {
                p2 = p2.next;
            }
        }
        //如果最后结果仍有进位，则需要再次新增节点
        if (carry > 0) {
            temp.next = new ListNode(1);
        }
        //返回实际头结点
        return initHeadNode.next;
    }


    /**
     * 扩展题解法
     *
     * @param l1 1
     * @param l2 2
     * @return: questions.ListNode
     * @auther: xianzilei
     * @date: 2019/10/7 8:05
     **/
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        //倒置链表l1和l2
        ListNode temp1 = reverse(l1);
        ListNode temp2 = reverse(l2);
        //求和
        ListNode temp3 = addTwoNumbers1(temp1, temp2);
        //倒置结果
        return reverse(temp3);
    }

    //反转链表（非递归实现）
    private static ListNode reverse(ListNode l) {

        if (l == null || l.next == null) {
            return l;
        }
        ListNode prev = l;
        ListNode cur = l.next;
        l.next = null;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    //反转链表（递归实现）
    private static ListNode reverseByRecursion(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode result = reverseByRecursion(head.next);
        head.next.next=head;
        head.next=null;
        return result;
    }

    //打印链表
    private static void printListNode(ListNode l) {
        ListNode p = l;
        StringBuilder str = new StringBuilder();
        while (p != null) {
            if (p != l) {
                str.append(" -> ");
            }
            str.append(p.val);
            p = p.next;
        }
        System.out.println(str);
    }

    public static void main(String[] args) {
        ListNode listNode11 = new ListNode(2);
        ListNode listNode12 = new ListNode(4);
        ListNode listNode13 = new ListNode(3);
        ListNode listNode14 = new ListNode(6);
        ListNode listNode15 = new ListNode(5);
        listNode11.next = listNode12;
        listNode12.next = listNode13;
        listNode13.next = listNode14;
        listNode14.next = listNode15;
        ListNode listNode21 = new ListNode(8);
        ListNode listNode22 = new ListNode(5);
        ListNode listNode23 = new ListNode(6);
        listNode21.next = listNode22;
        listNode22.next = listNode23;
        System.out.print("加数1：");
        printListNode(listNode11);
        System.out.print("加数2：");
        printListNode(listNode21);
        System.out.print("和：");
        printListNode(addTwoNumbers1(listNode11, listNode21));
        System.out.println("================================");
        System.out.print("加数1：");
        printListNode(listNode11);
        System.out.print("加数2：");
        printListNode(listNode21);
        System.out.print("和：");
        printListNode(addTwoNumbers2(listNode11, listNode21));
    }

}