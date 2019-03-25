package com.sankuai;

public class Queue<T> {

    private class  Node<T>{
        private T value;
        private Node<T> next;

        public T getValue() {
            return value;
        }
        public Node<T> getNext() {
            return next;
        }
        public Node(T value, Node<T> pre){
            this.value = value;
            if(pre != null) {
                pre.next = this;
            }
        }
    }

    private Node<T> head,tail;

    public void linkPush(T t){
        if(tail == null){
            tail = head = new Node<T>(t,null);
        }else{
           tail = new Node<T>(t,tail);
        }
    }

    public T linkTop(){
        return head.value;
    }

    public T linkPull(){
        T value = head.value;
        head     = head.next;
        return value;
    }

    public boolean isLinkEmpty(){
        return head == null;
    }

    public static void main(String[] args) {
        System.out.printf("hello world!");
        Queue<Integer> queue = new Queue<Integer>();
        System.out.println("isLinkEmpty:"+queue.isLinkEmpty());
        queue.linkPush(1);
        queue.linkPush(2);
        queue.linkPush(3);
        queue.linkPush(4);
        queue.linkPush(5);
        System.out.println(" pull "+queue.linkPull());
        System.out.println(" pull "+queue.linkPull());
    }
}
