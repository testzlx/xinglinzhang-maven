package com.sankuai.leetcode;

public class Leetcode_23 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode c= new ListNode(5);
        ListNode b= new ListNode(4);
        ListNode a= new ListNode(1);
        a.next = b;
        b.next = c;

        ListNode cc= new ListNode(4);
        ListNode bb= new ListNode(3);
        ListNode aa= new ListNode(1);
        aa.next = bb;
        bb.next = cc;

        ListNode bbb= new ListNode(6);
        ListNode aaa= new ListNode(2);
        aaa.next = bbb;
        ListNode[] lists = {aaa,aa,a};
        ListNode result = mergeKLists(lists);
        System.out.println(result);

    }

    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode head = null,node = null;

        while (true) {
            int min = Integer.MAX_VALUE,current = 0;
            boolean finish = true;
            for(int i =0;i < lists.length ;i++) {
                if(lists[i] != null) {
                    finish = false;
                    if(lists[i].val < min) {
                        min = lists[i].val;
                        current = i;
                    }
                }
            }
            if(finish) {
                break;
            }
            if(node == null) {
                head = node = new ListNode(lists[current].val);
            } else {
                node.next = new ListNode(lists[current].val);
                node =  node.next;
            }
            lists[current] = lists[current].next;


        }
        return head;

    }

}
