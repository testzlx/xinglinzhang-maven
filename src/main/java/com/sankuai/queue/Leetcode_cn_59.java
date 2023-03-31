package com.sankuai.queue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/*
个人理解： 把Deque从first到last按从大到小排序， 队列满了就poll掉first， 那么下一个first自动变为最大的，新offer一个元素
从last到first对比， poll掉小的。
*/
public class Leetcode_cn_59 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            return new int[0];
        }
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> queue = new ArrayDeque<>();
        for(int i = 0, j = 0; i < nums.length; i++) {
            if(!queue.isEmpty() && i - queue.peek() >= k) {
                queue.poll();
            }
            while(!queue.isEmpty() && nums[i] > nums[queue.peekLast()]) {
                queue.pollLast();
            }
            queue.offer(i);
            if(i >= k - 1) {
                res[j++] = nums[queue.peek()];
            }
        }

        return res;
    }


    public static void main(String[] args) {
        Leetcode_cn_59 main = new Leetcode_cn_59();
        int[] arr ={1,3,-1,2,5,3,6,7};
        int k =3;
        System.out.println(Arrays.toString(main.maxSlidingWindow(arr,3)));

    }
}
