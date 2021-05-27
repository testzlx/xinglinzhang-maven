package com.sankuai.memoization;

import java.util.*;

/**
 * 这个实现不是记忆化实现
 *
 */
public class Leetcode_zh_16 {

    public static void main(String[] args) {
        Leetcode_zh_16 leetcode_zh_16 = new Leetcode_zh_16();
        leetcode_zh_16.divingBoard(1,2,3);

    }


    public int[] divingBoard(int shorter, int longer, int k) {
        doDivingBoard(shorter,longer,k);
        return doDivingBoardv1(shorter,longer,k);
    }

    /**
     *  简单的做法
     *
     * @param shorter
     * @param longer
     * @param k
     * @return
     */
    private int[] doDivingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];
        }
        if (shorter == longer) {
            return new int[]{shorter * k};
        }
        int[] lengths = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            lengths[i] = shorter * (k - i) + longer * i;
        }
        return lengths;
    }


    /**
     *
     *
     * @param shorter
     * @param longer
     * @param k
     * @return
     */
    private int[] doDivingBoardv1(int shorter, int longer, int k) {
        int[] arr = {shorter,longer};
        Set<Integer> result = new HashSet<>();
        huisu(arr,k,0,0,result);
        return transfer(result);
    }

    /**
     *  个人理解是回溯法，深度遍历求出所有解，时间复杂度  power(arr.length,k);
     *
     * @param arr
     * @param k
     * @param index
     * @param sum
     * @param result
     */
    private void huisu(int[] arr, int k, int index, int sum, Set<Integer> result) {
        if (index == k){
            result.add(sum);
        } else {
            for (int i = 0;i<arr.length;i++) {
                sum+=arr[i];
                huisu(arr,k,index+1,sum,result);
                sum-=arr[i];
            }
        }
    }

    private int[] transfer(Set<Integer> result) {
        int[] ret = new int[result.size()];
        int i = 0;
        for (Iterator<Integer> it = result.iterator();it.hasNext();) {
            ret[i++] = it.next();
        }
        return ret;
    }

}
