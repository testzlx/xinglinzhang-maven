package com.sankuai.array;

import java.util.HashMap;
import java.util.Map;

public class Leetcode_cn_560 {

    private static int subarraySum(int[] nums,int k){
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        int sum = 0,ret =0;
        for (int num:nums){
            sum +=num;
            if(map.containsKey(sum-k)){
                ret += map.get(sum-k);
            }
            map.put(sum,map.getOrDefault(sum,0) +1);
        }
        return ret;
    }

    private static int subarraySum_v2(int[] nums,int k){
        int[] sums = new int[nums.length+1];
        int ret = 0;
        for (int i=1;i<=nums.length;i++){
            sums[i] = sums[i-1] +nums[i-1];
        }
        for(int i =1;i<sums.length;i++){
            for(int j=0;j<i;j++){
                if(sums[i] - sums[j] == k){
                    ret+=1;
                }
            }
        }
        return ret;
    }



    private static boolean checkSubarraySum(int[] nums,int k){
        if(nums.length<2){
            return false;
        }
        for(int i=1;i<nums.length;i++){
            if (nums[i] ==0 && nums[i-1] ==0){
                return true;
            }
        }
        int sum=0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        for(int i=0;i<nums.length;i++){
            sum +=nums[i];
            int mod = sum%k;
            if(map.containsKey(mod)){
                if(i - map.get(mod)>1){
                    return true;
                }
            }else{
                map.put(mod,i);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        boolean a = true;
        int[] nums ={1,2,2,4,0,8,4,4};
        int k = 0;
        System.out.println(subarraySum_v2(nums,k));

    }
}
