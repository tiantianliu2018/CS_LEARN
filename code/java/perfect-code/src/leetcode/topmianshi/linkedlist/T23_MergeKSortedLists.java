package leetcode.topmianshi.linkedlist;

import leetcode.topmianshi.ListNode;

import java.util.PriorityQueue;

/**
 * @author Kelly
 * @create 2020-06-03 11:52
 */
public class T23_MergeKSortedLists {
    /**
     * 先两个合并, 再进行合并，分治
     * 分治的时候，链表数组首尾两个合并，合并后的结果赋给首链表
     * 最终返回 lists[0] 即可
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        int n = lists.length;  // 要合并链表的个数
        while (n > 1){
            for (int i = 0; i < n / 2; i++) {
                lists[i] = mergeTwoList(lists[i], lists[n - 1 - i]);  // 两个链表合并赋给原来的链表
            }
            n = (n + 1) / 2;
        }
        return lists[0];
    }
    // 合并两个有序链表
    public ListNode mergeTwoList(ListNode node1, ListNode node2){
        if (node1 == null) return node2;
        if (node2 == null) return node1;
        ListNode newHead = new ListNode(0), curr = newHead;
        while (node1 != null && node2 != null){
            if (node1.val < node2.val) {
                curr.next = node1;
                node1 = node1.next;
            } else {
                curr.next = node2;
                node2 = node2.next;
            }
            curr = curr.next;
        }
        if (node1 != null) curr.next = node1;
        if (node2 != null) curr.next = node2;
        return newHead.next;
    }

    /**
     * 链表是有序的，因此可以把所有链表的头结点放在堆（最小堆）中，每次从堆中取节点放入最终结果链表
     * 然后将当前链表指针后移即可
     */
    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        // 建立一个小顶堆
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> ( a.val - b.val));
        ListNode dummy = new ListNode(0), curr = dummy;
        for (int i = 0; i < lists.length; i++) {
            // 将链表的首节点放在最小堆中
            if (lists[i] != null) heap.add(lists[i]);
        }
        // 不断从堆中取节点，如果取得的节点还有下一个节点，就将下一个节点加入堆中
        while (!heap.isEmpty()){
            ListNode node = heap.poll();
            curr.next = node;
            curr = curr.next;
            if (node.next != null) heap.add(node.next);
        }
        curr.next = null;
        return dummy.next;
    }
}
