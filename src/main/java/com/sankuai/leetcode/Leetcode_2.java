package com.sankuai.leetcode;

public class Leetcode_2 {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }
    public static void main(String[] args) {
        ListNode c= new ListNode(3);
        ListNode b= new ListNode(4);
        ListNode a= new ListNode(2);
        a.next = b;
        b.next = c;

        ListNode cc= new ListNode(9);
        ListNode bb= new ListNode(6);
        ListNode aa= new ListNode(1);
        aa.next = bb;
        bb.next = cc;
        ListNode result = addTwoNumbers(a,aa);
        System.out.println(result.val);

    }

    public  static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }


}
