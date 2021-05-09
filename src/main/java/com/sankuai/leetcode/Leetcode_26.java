package com.sankuai.leetcode;

public class Leetcode_26 {

    class ListNode {
        int val;
        ListNode next;
    }

    public static void main(String[] args) {
        Leetcode_26 leetcode_26 = new Leetcode_26();
        ListNode head = leetcode_26.insertList();
        leetcode_26.reverseKGroup(head,2);
        System.out.println("hello world!");
    }

    private ListNode insertList() {
        int[] arr = {1,2,3,4,5};
        ListNode head = null,curr = null;
        for(int i = 0 ;i <= 4;i++) {
            ListNode listNode = new ListNode();
            listNode.val = arr[i];
            if(head == null) {
                head = listNode;
            } else {
                curr.next = listNode;
            }
            curr = listNode;
        }
        curr.next = null;
        return head;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (!canReverseKGroup(head, k)) {
            return head;
        }
        ListNode pre = null, curr = head, nxt = null;
        int count = 0;
        while (count != k) {
            count++;
            nxt = curr.next;
            curr.next = pre;
            pre = curr;
            curr = nxt;
        }
        if (curr != null) {
            head.next = reverseKGroup(curr, k);
        }
        return pre;
    }

    private boolean canReverseKGroup(ListNode head, int k) {
        int count = 0;
        while (head != null && count != k) {
            count++;
            head = head.next;
        }
        return count == k;
    }
}
