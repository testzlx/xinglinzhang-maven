package com.sankuai.datastruct;

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

    //找到链表的中间节点（快慢指针）
    public Node findMid(Node head){
        if(head == null || head.next == null){
            return head;
        }
        Node pre = head;
        Node p = head.next;
        Node q = head.next.next;

        while (q != null && q.next != null){
            pre = pre.next;
            p = pre.next;
            q = q.next.next;
        }
        return p;
    }

    //链表的快速排序实现
    public Node quickSort(Node head){
         quickSort(head,null);
         return head;
    }

    private void quickSort(Node head, Node tail) {
        if(head != tail){
            Node partition = getPartition(head,tail);
            quickSort(head,partition);
            quickSort(partition.next,tail);
        }
    }

    private <T> void swap(Node<T> p,Node<T> q){
        T tmp = p.value;
        p.value = q.value;
        q.value = tmp;

    }

    private Node getPartition(Node head, Node tail) {
        if(head == null ||head.next == tail){
            return head;
        }
        Node p = head,q = head.next;
        while(q != tail){
            if ((int)q.value < (int)head.value){
                p = p.next;
                swap(p,q);
            }
            q = q.next;
        }
        swap(head,p);
        return p;
    }

    public void clear(Node node) {
        while(node != null) {
            Node next = node.next;
            node.value = null;
            node.next = null;
            node = null;
            node = next;
        }
        this.head = null;
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
        System.out.println("------------------------------------------");
        myList.clear(myList.head);
        myList.insert(6);
        myList.insert(5);
        myList.insert(4);
        myList.insert(3);
        myList.insert(2);
        myList.insert(1);
        Node mid = myList.findMid(myList.head);
        System.out.println(mid.value);
        System.out.println("------------------快速排序------------------------");
        myList.clear(myList.head);
        myList.insert(3);
        myList.insert(4);
        myList.insert(9);
        myList.insert(2);
        myList.insert(1);
        myList.insert(5);
        myList.quickSort(myList.head);
        System.out.println(myList.head.value);
        System.out.println("-----------------归并排序------------------------");
        myList.clear(myList.head);
        myList.insert(3);
        myList.insert(4);
        myList.insert(9);
        myList.insert(2);
        myList.insert(1);
        myList.insert(5);
        Node mergeSortNode = myList.mergeSort(myList.head);
        System.out.println(mergeSortNode.value);
    }

    public Node mergeSort(Node head) {
        if (head == null || head.next == null){
            return head;
        }
        Node pre =head,curNode = head.next,nextNode = head.next.next;
        while(nextNode != null && nextNode.next != null){
            pre = pre.next;
            curNode = pre.next;
            nextNode = nextNode.next.next;
        }
        pre.next = null;
        Node node1 = mergeSort(head);
        Node node2 = mergeSort(curNode);
        return merge(node1,node2);
    }

    private Node merge(Node node1, Node node2) {
        Node head = new Node(0,null);
        Node tmp = head;
        while(node1 != null && node2 !=null){
            if((int)node1.value < (int)node2.value){
                tmp.next = node1;
                node1 = node1.next;
            }else {
                tmp.next = node2;
                node2 = node2.next;
            }
            tmp = tmp.next;
        }
        if (node1 != null)  {
            tmp.next = node1;
        }
        if (node2 != null) {
            tmp.next = node2;
        }
        return head.next;
    }

    //  leetcode_cn_141,判断链表是否有环
    public boolean hasCycle(Node head) {
        Node p = head,q = head.next;
        while(p != null && q != null && q.next != null){
            if (p == q) {
                return true;
            }
            p = p.next;
            q = q.next.next;
        }
        return false;

    }

    //  判断两链表是否相交,leetcode_cn160
    public Node getIntersectionNode(Node headA, Node headB) {
        if (headA == null || headB == null){
            return  null;
        }
        Node pA = headA,pB = headB;
        while(pA != pB) {
            pA = pA == null ? pB:pA.next;
            pB = pB == null ? pA:pB.next;
        }
        return pA;

    }


    public Node removeNthFromEnd(Node head, int n) {
        if (head == null || head.next == null) {
            return null;
        }
        Node p = head,q = head,afterN = null;
        while( n>0) {
            p = p.next;
            n--;
        }
        afterN = p;
        if (afterN == null) {
            return head.next;
        }
        while (afterN.next != null) {
            afterN = afterN.next;
            q= q.next;
        }
        Node tmp = q.next;
        q.next = tmp.next;
        tmp = null;

        return head;

    }

    //leetcode_cn_147. 核心思想是新开一条链表，不在原有链表上操作
    public Node<Integer> insertionSortList(Node<Integer> head) {
        Node<Integer> dummy = new Node(-1,null);
        while(head != null){
            Node<Integer> next = head.next;
            Node<Integer> p = dummy;
            while (p.next != null && (Integer)p.next.value < head.value){
                 p =p.next;
            }
            head.next = p.next;
            p.next = head;
            head = next;
        }

        return dummy.next;
    }
}
