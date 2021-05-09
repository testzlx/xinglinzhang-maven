package com.visualbusiness.my_test;

public class Main1 {

    Node head = null;
    class Node{
        int val;
        Node next;
        Node(int value) {
            this.val = value;
        }
    }
    public static void main(String[] args) {
        Main1 main1 = new Main1();
        main1.insert(5);
        main1.insert(4);
        main1.insert(3);
        main1.insert(2);
        main1.insert(1);
        main1.print(main1.head);
        Node newHead =  main1.reverse(main1.head);
        System.out.println("-------------------");
        main1.print(newHead);

    }
    public void insert(int value) {
        Node node = new Node(value);
        if (head== null) {
            head  = node;
        } else {
            node.next = head;
            head = node;
        }
    }

    public Node reverse(Node head) {
        Node pre = null,curr = head,nxt = null;
        while (curr != null) {
            nxt = curr.next;
            curr.next = pre;
            pre = curr;
            curr = nxt;
        }
        return pre;
    }

    public void print(Node head) {
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }

    }
}
