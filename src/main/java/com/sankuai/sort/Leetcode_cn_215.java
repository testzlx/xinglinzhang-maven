package com.sankuai.sort;

import java.util.Random;

public class Leetcode_cn_215 {

    Random random = new Random();

    public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums,0,nums.length-1,nums.length-k);
    }

    private int findKthLargest(int[] nums, int left, int right, int index) {
        int tmp = getPartition(nums,left,right);
        if(tmp == index){
            return nums[tmp];
        }else{
            return tmp < index ? findKthLargest(nums,tmp+1,right,index):findKthLargest(nums,left,tmp-1,index);
        }
    }

    private int getPartition(int[] nums, int left, int right) {
        int k = random.nextInt(right-left+1)+left;
        swap(nums,k,right);
        return partition(nums,left,right);
    }

    private int partition(int[] nums, int left, int right) {
        int tmp = nums[right],storeIndex = left;
        for(int i=left;i<right;i++){
            if(nums[i] <tmp){
                swap(nums,i,storeIndex++);
            }
        }
        swap(nums,storeIndex,right);
        return storeIndex;
    }

    private void swap(int[] nums, int k, int right) {
        int tmp = nums[right];
        nums[right] = nums[k];
        nums[k] = tmp;
    }

    public static void main(String[] args) {
        int[] arr ={1,4,3,7,2,4,7};
        int k =2;
        Leetcode_cn_215  leetcode_cn_215 = new Leetcode_cn_215();
        System.out.println(leetcode_cn_215.findKthLargest(arr,k));
    }
}
