package com.sankuai.prefixsum;

public class Leetcode_cn_209 {

    //前缀和+滑动窗口
    public int minSubArrayLen(int target, int[] nums) {
        int sum = 0,j=0,len = 0;
        for(int i=0;i<nums.length;i++){
            sum += nums[i];
            while(sum >=target){
                len = len ==0? i-j+1 :Math.min(len,i-j+1);
                sum -= nums[j++];
            }
        }
        return len;
    }


    public static void main(String[] args) {

    }
}
