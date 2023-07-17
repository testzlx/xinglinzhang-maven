package com.sankuai.queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class Offer_59 {

    public static void main(String[] args) {
        MaxQueue queue = new MaxQueue();
        queue.push_back(1);
        queue.push_back(3);
        queue.push_back(2);
        System.out.println(queue.max_value());
        queue.pop_front();
        System.out.println(queue.max_value());
        queue.pop_front();
        System.out.println(queue.max_value());
    }

    static class MaxQueue {
        Deque<Integer> help;
        Deque<Integer> queue;

        public MaxQueue() {
            help = new ArrayDeque<>();
            queue = new ArrayDeque<>();

        }

        public int max_value() {
            return queue.isEmpty()?-1:help.peekFirst();

        }

        public void push_back(int value) {
            queue.offer(value);
            while(!help.isEmpty() && value > help.peekFirst()){
                help.pollFirst();
            }
            help.addLast(value);

        }

        public int pop_front() {
            if(queue.isEmpty()){
                return -1;
            }
            int val = queue.pollFirst();
            if(val == help.peekFirst()){
                help.pollFirst();
            }
            return val;

        }
    }
}
