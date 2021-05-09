package com.sankuai.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Leetcode_15 {
    public static void main(String[] args) {
        int[] num = {-2,-1,0,1,2,-1,-4};
        System.out.println(threeSum(num));

    }

    private static List<List<Integer>> threeSum(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0 ;i<num.length-2 ; i++){
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
                   }else if( num[left] + num[right] < sum) {
                       left++;
                   }else {
                       right --;
                   }
               }
            }
        }
        return res;
    }
}
