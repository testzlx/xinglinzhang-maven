package com.sankuai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode_zh_78 {

    private static List<Integer> path = new ArrayList<>();
    private  static List<List<Integer>> results = new ArrayList<>();


    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        ret.add(new ArrayList<Integer>());
        for(int val:nums){
            int size = ret.size();
            for(int i=0;i<size;i++){
                List<Integer> tmp = new ArrayList<>(ret.get(i));
                tmp.add(val);
                ret.add(tmp);
            }
        }
        return ret;
    }

    // 类似题目 leet_code_90
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        ret.add(new ArrayList<Integer>());
        Arrays.sort(nums);
        int left =0 ,right=1,len=0;
        for(int i=0;i < nums.length;i++){
            if (i!=0 && nums[i] == nums[i-1]){
                left = ret.size() - len;
            }else{
                left = 0;
            }
            right = ret.size();
            len = right - left;
            for(int j=left;j<right;j++){
                List<Integer> tmp = new ArrayList<>(ret.get(j));
                tmp.add(nums[i]);
                ret.add(tmp);
            }
        }
        return ret;
    }

    // 回溯法处理
    public static List<List<Integer>> subsets_v2(int[] nums) {
        results.clear();
        backtrace(nums,0);
        return results;
    }

    private static void backtrace(int[] nums, int start_index) {
        results.add(new ArrayList<>(path));
        if(start_index>= results.size()){
            return;
        }
        for(int i =start_index;i<nums.length;i++){
            path.add(nums[i]);
            backtrace(nums,i+1);
            path.remove(path.size()-1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        for(List<Integer> item:subsets_v2(nums)){
            System.out.println(Arrays.toString(item.toArray()));
        }
        System.out.println("------------------------------------");
        int[] numsWithDup = {1,2,3,3};
        for(List<Integer> item:subsetsWithDup(numsWithDup)){
            System.out.println(Arrays.toString(item.toArray()));
        }

    }
}
