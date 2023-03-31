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

    //leetcode_92
    public Node reverseMN(Node head,int m,int n){
        if (head == null){
            return null;
        }
        if (m == n){
            return head;
        }
        Node firstHead = null,firstTail=null,secondHead=null,secondTail=null,thirdHead=null,pre=null;
        firstHead = head;
        int i = 0;
        while (head != null) {
            if (++i ==m){
                secondHead = head;
                firstTail = pre;
            }else if (i ==n){
                secondTail = head;
                break;

            }
            pre = head;
            head = head.next;
        }
        if(head != null){
            thirdHead = head.next;
        }
        Node node = secondHead;
        pre = null;
        Node tmp = secondTail.next;
        while(node != tmp) {
            Node next = node.next;
            node.next=pre;
            pre = node;
            node = next;
        }
        if (m==1){
            firstHead = secondTail;
        }else{
            firstTail.next = secondTail;
        }

        secondHead.next = thirdHead;

        return firstHead;
    }

    //给n个有序链表重排序
    public Node mergeKLists(Node[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if(lists.length == 1){
            return lists[0];
        }
        return mergeLists(lists,0,lists.length-1);

    }

    private Node mergeLists(Node[] lists, int left, int right) {
        if (left == right){
            return lists[left];
        }
        int mid = (left+right)/2;
        Node leftNode = mergeLists(lists,left,mid);
        Node rightNode = mergeLists(lists,mid+1,right);
        Node root = new Node(Integer.MIN_VALUE,null);
        Node p = root;
        while (leftNode != null && rightNode != null) {
           int leftval = (int)leftNode.value;
           int rightVal = (int)rightNode.value;
           if (leftval <= rightVal){
               p.next = leftNode;
               leftNode = leftNode.next;
           }else{
               p.next = rightNode;
               rightNode = rightNode.next;
           }
           p = p.next;
        }
        if (leftNode != null) {
            p.next = leftNode;
        }
        if (rightNode != null) {
            p.next = rightNode;
        }
        return root.next;
    }

    public static void main(String[] args) {

        MyLinkedList<Integer> myList = new MyLinkedList<Integer>();
        myList.insert(5);
        myList.insert(4);
        myList.insert(3);
        myList.insert(2);
        myList.insert(1);
        //myList.reverse(myList);
        Node node = myList.reverseMN(myList.head,1,4);
        //Node node = myList.reverse2(myList.head);
        System.out.println("hello world!!!");

        Node a5 = new Node(5,null);
        Node a4 = new Node(4,a5);
        Node a1 = new Node(1,a4);

        Node b4 = new Node(4,null);
        Node b3 = new Node(3,b4);
        Node b1 = new Node(1,b3);

        Node c6 = new Node(6,null);
        Node c2 = new Node(2,c6);
        Node[] lists = {a1,b1,c2};
        Node head = myList.mergeKLists(lists);
        System.out.println(head.value);
    }
}
