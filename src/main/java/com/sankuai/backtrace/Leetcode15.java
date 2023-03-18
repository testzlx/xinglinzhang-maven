package com.sankuai.backtrace;

import java.util.*;
import java.util.stream.Collectors;


public class Leetcode15 {

    public static void main(String[] args) {
        Leetcode15 leetcode15  = new Leetcode15();
        int[] nums =  {-1,0,1,2,-1,-4};
        List<List<Integer>> results =leetcode15.threeSum(nums);
        for(List<Integer> pair:results){
            System.out.println(Arrays.toString(pair.toArray()));
        }

    }
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        int[] arr = new int[3];
        fn(nums,nums.length,0,3,arr,list);
        return list;
    }

    public List<List<Integer>> threeSumV2(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0 ;i<num.length ; i++){
            if (i == 0 || num[i] != num[i-1]) {
                int left = i+1,right = num.length -1, sum = 0 - num[i];
                while (left < right) {
                    if ( num[left] + num[right] == sum) {
                        res.add( Arrays.asList(num[i],num[left],num[right]));
                        while (left < right && num[left] == num[left+1]) {
                            left++;
                        }
                        while (left < right && num[right] == num[right-1]) {
                            right--;
                        }
                        left++;
                        right--;
                    }else if( num[left] + num[right] <sum) {
                        left++;
                    }else {
                        right --;
                    }
                }
            }
        }
        return res;
    }


    /**
     *
     * @param nums 输入
     * @param target 目标和
     * @param size 数组大小
     * @param index n个数字
     * @param arr  临时和
     * @param list 存储结果
     */
    private void fn(int[] nums,int size, int target, int index, int[] arr, List<List<Integer>> list) {
        for (int i = size-1; i>=index-1;i--) {
            arr[index-1] =  nums[i];
            if (index >1) {
                fn(nums,i,target,index-1,arr,list);
            }else {
                if(isSum(arr,target)) {
                    addtoList(list,arr);
                }
            }
        }
    }

    private void addtoList(List<List<Integer>> list, int[] arr) {
        boolean flag = true;
        for(List<Integer> val : list) {
            for(int i : arr) {
                if(!val.contains(i)) {
                    break;
                }
                flag = false;
            }
        }
        if(flag) {
            list.add(Arrays.stream(arr).boxed().collect(Collectors.toList()));
        }
    }

    private static boolean isSum(int[] b,int suM) {
        int sum = 0;
        for(int i:b){
            sum += i;
        }
        if(sum == suM)
            return true;
        return false;
    }
}
