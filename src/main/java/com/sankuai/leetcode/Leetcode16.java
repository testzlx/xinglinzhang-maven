package com.sankuai.leetcode;

import java.util.Arrays;

public class Leetcode16 {

    public int threeSumClosest(int[] num, int target) {
        Arrays.sort(num);
        int diff = Integer.MAX_VALUE,close =0;
        for (int i = 0 ;i<num.length ; i++){
                int left = i+1,right = num.length -1;
                while (left < right) {
                    int sum = num[left] + num[right] + num[i];
                    if (sum == target) {
                        return target;
                    }
                    if(sum < target) {
                        left++;
                    }else {
                        right--;
                    }
                    int tmpDiff = Math.abs(target -sum);
                    if(tmpDiff < diff) {
                        diff = tmpDiff;
                        close = sum;
                    }
                }
            }
        return close;
        }
}
