package com.sankuai;

public class MyLinkedList<T> {

    Node head;

    static class Node<T> {
        T value;
        Node next;

        Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public void insert(T value) {
        Node node = new Node(value, head);
        head = node;
    }

    public   void reverse(MyLinkedList myLinkedList){
        if(myLinkedList == null || myLinkedList.head == null){
            return ;
        }
        Node node = myLinkedList.head,pre =null;
        while(node != null){
            Node after = node.next;
            node.next = pre;
            pre = node;
            node = after;
        }
        head = pre;
    }
    //递归反转
    public Node reverse2(Node node){
        if(node == null || node.next == null){
            return node;
        }
        Node after = reverse2(node.next);
        node.next.next = node;
        node.next = null;
        return after;
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> myList = new MyLinkedList<Integer>();
        myList.insert(4);
        myList.insert(3);
        myList.insert(2);
        myList.insert(1);
        //myList.reverse(myList);
        Node node = myList.reverse2(myList.head);
        System.out.println("hello world!!!");
    }
}
