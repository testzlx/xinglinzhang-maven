package com.sankuai.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_1 {
    public static void main(String[] args) {
        int[] arr = {2,7,11,15};
        int[] result =  twoSum(17,arr);
        System.out.println(result[0] + " "+ result[1]);

    }
    private static int[] twoSum(int target,int[] nums) {
        Map<Integer,Integer> hmap = new HashMap<>();
        for(int i =0 ;i < nums.length;i++) {
            hmap.put(nums[i],i);
            if (hmap.get(target - nums[i]) != null) {
               return new int[]{i,hmap.get(target - nums[i])};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
