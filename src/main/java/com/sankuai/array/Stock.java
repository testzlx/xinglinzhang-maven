package com.sankuai.array;

import java.util.Arrays;

/**
 * 股票买卖收益最大化
 *
 * @author zhanglinxing
 */
public class Stock {
    private static int[] prices = {11, 13, 24, 29, 7, 2, 28, 1, 4, 5};
    //private int[] prices = { 11, 10};

    public static void main(String[] args) {
        Stock stock = new Stock();
        stock.maxBenifit();
        int maxSum = serialSum(prices);
        System.out.println("maxSum: " + maxSum);
        int[] prize = {3,2,6,5,0,3};
        System.out.println(stock.maxProfit(2,prize));
    }

    private void maxBenifit() {
        int max = 0, buy = prices[0];
        for (int i = 1; i < prices.length; i++) {
            int tmp = prices[i];
            if (tmp > buy) {
                if (tmp - buy > max) {
                    max = tmp - buy;
                }
            } else {
                buy = tmp;
            }
        }
        System.out.println("max:" + max);
    }

    //leetcode_cn_122
    public int maxProfit(int[] prices) {
        int ans = 0;
        for (int i = 1; i <= prices.length - 1; i++) {
            if (prices[i] > prices[i - 1]) {
                ans += prices[i] - prices[i - 1];
            }
        }
        return ans;
    }

    //leetcode_vn_188
    public int maxProfit(int k, int[] prices) {
        //todo
        return -1;
    }

    //和最大的连续子串，返回和值
    //如果要求出连续子串的起始位置和介绍位置，个人理解：只能求出终止位置，再根据终止位置和和值推出起始位置
    //https://www.jianshu.com/p/611eb202be1e
    private static int serialSum(int nums[]) {
        int start = 0, end = 0;
        int local[] = {2, -1, 3, 4, -50, -7, -6, 10};
        int maxSum = local[0], sum = local[0];
        for (int i = 1; i < local.length; i++) {
            sum += local[i];
            if (sum > maxSum) {
                maxSum = sum;

                end = i;
            } else {
                if (sum < 0) {
                    sum = 0;
                    //	start = i+1;
                }
            }
        }
        return maxSum;
    }
}
