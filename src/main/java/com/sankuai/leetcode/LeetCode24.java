package com.sankuai.leetcode;

public class LeetCode24 {
    public ListNode swapPairs(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            ListNode second = cur.next,thrid = null;
            if (second != null) {
                int tmp = cur.val;
                cur.val = second.val;
                second.val = tmp;
                thrid = second.next;
            }
            cur = thrid;
        }
        return head;
    }
     class ListNode {
        int val;
        ListNode next;
     }
}
