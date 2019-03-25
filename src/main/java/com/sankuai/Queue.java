package com.sankuai;

import java.lang.reflect.Array;

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

    public static class ArrayQueue<T>{
        private Integer front = 0,rear = 0;
        private T[] arrs;

        public ArrayQueue(int size,Class<T> type) throws Exception {
            //attention 泛型数组不能直接定义
         arrs = (T[])Array.newInstance(type,size+1);

        }

        public void linkPush(T t){
            if(!isFull()) {
                arrs[rear] = t;
                rear = (++rear) % arrs.length;
            }

        }

        public T linkTop(){
            return arrs[front];
        }

        public T linkPull(){
            if(!isLinkEmpty()) {
                T value =  arrs[front];
                front = (++front) % arrs.length;
                return value;
            }
            return null;
        }

        public boolean isLinkEmpty() {
            return rear == front;
        }
        public boolean isFull(){
            return (rear+1)%arrs.length == front;
        }
    }

    public static void main(String[] args) throws Exception {
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
        System.out.println("----------------------------------------------------------------------");
        Queue.ArrayQueue<Integer> arrayQueue = new Queue.ArrayQueue<Integer>(5,Integer.class);
        arrayQueue.linkPush(0);
        arrayQueue.linkPush(1);
        arrayQueue.linkPush(2);
        System.out.println(arrayQueue.linkPull());
        arrayQueue.linkPush(3);
        arrayQueue.linkPush(4);
        arrayQueue.linkPush(5);
        arrayQueue.linkPush(6);
        System.out.println(arrayQueue.linkPull());
        System.out.println(arrayQueue.linkPull());
        System.out.println(arrayQueue.linkPull());
        System.out.println(arrayQueue.linkPull());
        System.out.println(arrayQueue.linkPull());


    }
}
