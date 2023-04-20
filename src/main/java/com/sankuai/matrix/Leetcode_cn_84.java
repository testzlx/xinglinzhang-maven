package com.sankuai.matrix;


import java.util.ArrayDeque;
import java.util.Deque;

// 单调栈思想
public class Leetcode_cn_84 {

    public int largestRectangleArea(int[] heights) {
        int area = 0;
        int[] newHeights = new int[heights.length+2];
        System.arraycopy(heights,0,newHeights,1,heights.length);
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i =0;i<newHeights.length;i++){
            while(!stack.isEmpty() && newHeights[i] < newHeights[stack.peek()]){
                int h = newHeights[stack.pop()];
                area = Math.max(area,h*(i-stack.peek()-1));
            }
            stack.push(i);
        }
        return area;
    }


    public static void main(String[] args) {
        Leetcode_cn_84 leetcode_cn_84 = new Leetcode_cn_84();
        int[] arr ={2,1,5,6,2,3};
        int area = leetcode_cn_84.largestRectangleArea(arr);
        System.out.println(area);
    }

}
