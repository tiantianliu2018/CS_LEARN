package leetcode.datastructure.linklist;

/**
 * @author Kelly
 * @create 2020-04-24 11:28
 */
public class MyLinkedList {
    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
            next = null;
        }
    }

    private ListNode head;
    private ListNode tail;
    private int size;

    public MyLinkedList() {
        head = new ListNode(0);
        tail = head;
        size = 0;
    }

    public int get(int index) {
        ListNode curr = head.next;
        while(index > 0 && curr != null){
            curr = curr.next;
            index--;
        }
        if(index == 0) return curr.val;
        return -1;
    }

    public void addAtHead(int val) {
        ListNode node = new ListNode(val);
        if (head.next == null){  // 是第一个节点
            tail = node;
        }
        node.next = head.next;
        head.next = node;
        size++;
    }
    public void addAtTail(int val) {
        ListNode node = new ListNode(val);
        tail.next = node;
        tail = node;
        size++;
    }

    public void addAtIndex(int index, int val) {
        if(index > size) return;
        if(index == size) {
            addAtTail(val);
            return;
        }
        if(index == 0) {
            addAtHead(val);
            return;
        }

        ListNode pre = head.next;
        while(index > 1){
            pre = pre.next;
            index--;
        }

        ListNode node = new ListNode(val);
        node.next = pre.next;
        pre.next = node;
        size++;
    }
    public void deleteAtIndex(int index) {
        if(index == 0) head.next = head.next.next;

        if(index > 0 && index <= size){
            ListNode pre = head.next;
            while(index > 1){
                pre =  pre.next;
                index--;
            }
            if(pre.next == null) {
                tail = pre;
                return;
            }
            pre.next = pre.next.next;
        }
    }

    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();

        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(1,2);
        int i = linkedList.get(1);
        System.out.println(i);
        linkedList.deleteAtIndex(1);
        linkedList.get(1);
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */