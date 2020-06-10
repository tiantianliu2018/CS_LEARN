package leetcode.topmianshi.linkedlist;

import leetcode.topmianshi.ListNode;

/**
 * @author Kelly
 * @create 2020-05-22 09:57
 *
 * 给出两个非空的链表用来表示两个非负的整数。其中，它们各自的位数是按照逆序的方式存储的，并且它们的每个节点只能存储一位数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头
 */
public class T2_AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummy = new ListNode(0);  // 虚拟头结点
        ListNode pre = dummy;
        int carry = 0;  // 存储进位
        while (l1 != null || l2 != null || carry != 0){  // 最后判断是否还有进位就不用单独写了
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;
            carry = sum >= 10 ? 1 : 0;
            ListNode curr = new ListNode(sum % 10);
            pre.next = curr;
            pre = curr;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return dummy.next;
    }
}
