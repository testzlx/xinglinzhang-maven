package com.sankuai;

import java.lang.reflect.Array;

public class Stack<T> {
    private int top  = -1;T[] arr;

    public Stack(int size,Class<?> clazz) {
        arr = (T[]) Array.newInstance(clazz,size);
    }
    public T pop(){
        if(!isEmpty()) {
            return arr[top--];
        }else{
            return null;
        }
    }

    public void push(T t){
        if(!isFull()) {
            arr[++top] = t;
        }
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public boolean isFull(){
        return top == arr.length -1;
    }

    private  static class  Node<T>{
        private T value;
        private Node<T> next;

        public T getValue() {
            return value;
        }
        public Node<T> getNext() {
            return next;
        }
        public Node(T value, Node<T> next){
            this.value = value;
            this.next = next;
        }
    }

    public static class LinkedStack<T>{
        private Node<T> top;
        public T pop(){
            if(!isEmpty()) {
                T value = top.value;
                top = top.next;
                return value;
            }else{
                return null;
            }
        }

        public void push(T t){
            Node<T> node = new Node<>(t,top);
            top = node;
        }

        public boolean isEmpty(){
            return top == null;
        }
    }

    public static class  ShareStack<T>{
        private int top1 = -1,top2 = -1,size;
        private T[] arr;

        public ShareStack(int size,Class<T> clazz){
            arr = (T[]) Array.newInstance(clazz,size);
            this.size = size;
        }
        public T lpop(){
            if(!lIsEmpty()) {
                return arr[top1--];
            }else{
                return null;
            }
        }
        public T rpop(){
            if(!rIsEmpty()) {
                return arr[4-(top2--)];
            }else{
                return null;
            }
        }

        public void lpush(T t){
            if(!isFull()) {
                arr[++top1] = t;
            }
        }
        public void rpush(T t){
            if(!isFull()) {
                arr[4-(++top2)] = t;
            }
        }

        public boolean isEmpty(){
            return lIsEmpty() && rIsEmpty();
        }

        private boolean lIsEmpty(){
            return top1 == -1;
        }

        private boolean rIsEmpty(){
            return top2 == -1;
        }

        public boolean isFull(){
            return top1 +top2 == arr.length -2 ;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>(5,Integer.class);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(8);
        stack.push(9);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println("----------------------------------------------------");
        Stack.LinkedStack<Integer> linkedStack = new Stack.LinkedStack<Integer>();
        linkedStack.push(1);
        linkedStack.push(2);
        linkedStack.push(3);
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        System.out.println("--------------------shareStack--------------------------------");
        Stack.ShareStack<Integer> shareStack = new Stack.ShareStack<Integer>(5,Integer.class);
        shareStack.lpush(1);
        shareStack.rpush(2);
        shareStack.rpush(3);
        shareStack.rpush(4);
        shareStack.rpush(5);
        shareStack.rpush(6);
        shareStack.rpush(7);
        System.out.println(shareStack.lpop());
        System.out.println(shareStack.lpop());
        System.out.println(shareStack.rpop());
        System.out.println(shareStack.rpop());
        System.out.println(shareStack.rpop());
        System.out.println(shareStack.rpop());
        System.out.println(shareStack.rpop());
        System.out.println(shareStack.rpop());

    }
}
