package leetcode.topmianshi.linkedlist;

import leetcode.topmianshi.ListNode;

/**
 * @author Kelly
 * @create 2020-05-29 09:18
 */
public class T19_RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        ListNode slow = head, fast = head;
        while (fast.next != null && n > 0){
            fast = fast.next;
            n--;
        }
        if (n > 0) return head.next;  // 说明应该删除第一个节点
        while (fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }
        if (slow.next != null) slow.next = slow.next.next;
        else slow.next = null;
        return head;
    }
}
