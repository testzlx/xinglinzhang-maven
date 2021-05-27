package com.sankuai.random;

import java.util.Random;

public class LeetCode_zh_528 {
    int[] tmp;
    int sum = 0;
    Random random = new Random();

    public static void main(String[] args) {
        int arr[]= {2,3,5};
        LeetCode_zh_528 leetCode_zh_528 = new LeetCode_zh_528(arr);
        for(int i = 0;i< 100; i++)
            System.out.println(arr[leetCode_zh_528.pickIndex()]);

    }
    public LeetCode_zh_528(int[] w) {
        tmp = new int[w.length];
        for (int i=0;i<w.length;i++) {
            sum += w[i];
            tmp[i] = sum;
        }
    }

    public int pickIndex() {
        int rd = random.nextInt(sum);
        int left = 0 ,right = tmp.length -1;
        while (left != right) {
            int mid = (left + right)/2;
            if(rd >= tmp[mid]) {
                left = mid+1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
