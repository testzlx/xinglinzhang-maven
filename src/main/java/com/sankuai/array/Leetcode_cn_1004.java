package com.sankuai.array;

public class Leetcode_cn_1004 {
    //滑动窗口思想
    public static int longestOnes(int[] nums, int k) {
        int left = 0, right = 0;
        int res = Integer.MIN_VALUE;
        while(right < nums.length){
            if(nums[right] == 0)k--;
            right++;

            while(k < 0){//左边界需要收缩
                if(nums[left] == 0)k++;
                left++;
            }
            res = Math.max(res, right - left);
        }
        return res;

    }


    public static void main(String[] args) {
        int arr[] = {1,1,1,0,0,0,1,1,1,1,0}, k = 2;
        System.out.println(longestOnes(arr,2));

    }
}
