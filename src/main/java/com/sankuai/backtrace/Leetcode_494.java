package com.sankuai.backtrace;

import java.util.Arrays;

/**
 * https://leetcode.com/articles/target-sum/
 * @author zhanglinxing
 * @date 2017年5月25日 下午4:36:49
 */
public class Leetcode_494 {
	int count = 0;
    public int findTargetSumWays(int[] nums, int S) {
        int[][] memo = new int[nums.length][2001];
        for (int[] row: memo)
            Arrays.fill(row, Integer.MIN_VALUE);
        return calculate(nums, 0, 0, S, memo);
    }
    public int calculate(int[] nums, int i, int sum, int S, int[][] memo) {
        if (i == nums.length) {
            if (sum == S)
                return 1;
            else
                return 0;
        } else {
            if (memo[i][sum + 1000] != Integer.MIN_VALUE) {
                return memo[i][sum + 1000];
            }
            int add = calculate(nums, i + 1, sum + nums[i], S, memo);
            int subtract = calculate(nums, i + 1, sum - nums[i], S, memo);
            memo[i][sum + 1000] = add + subtract;
            return memo[i][sum + 1000];
        }
    }
    
    public int findUnsortedSubarray(int[] A) {
        int n = A.length, beg = -1, end = -2, min = A[n-1], max = A[0];
        for (int i=1;i<n;i++) {
          max = Math.max(max, A[i]);
          min = Math.min(min, A[n-1-i]);
          if (A[i] < max) end = i;
          if (A[n-1-i] > min) beg = n-1-i; 
        }
        return end - beg + 1;
    }
    
    public static void main(String[] args) {
		int nums[] = {1,1,1,1,1},sum=3;
		Leetcode_494 solution = new Leetcode_494();
		System.out.println(solution.findTargetSumWays(nums, sum));
		int A[] ={2,4,7,6,5,10,12,8,9};
		System.out.println(solution.findUnsortedSubarray(A));
		
	}
}
