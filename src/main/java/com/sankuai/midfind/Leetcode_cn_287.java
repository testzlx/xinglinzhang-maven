package com.sankuai.midfind;

public class Leetcode_cn_287 {

    public int findDuplicate(int[] nums) {
        int ans = -1;
        int left =1,right = nums.length-1;
        while(left <= right){
            int mid = (left+right) >>1;
            int count =0;
            for(int i=0;i<=nums.length-1;i++){
                if(nums[i] <= mid){
                    count++;
                }
            }
            if(count <=mid){
                left = mid+1;
            }else{
                right = mid-1;
                ans = mid;
            }
        }
        return ans;

    }

    public static void main(String[] args) {

    }
}
