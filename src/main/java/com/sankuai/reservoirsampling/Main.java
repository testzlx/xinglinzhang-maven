package com.sankuai.reservoirsampling;


import java.util.Arrays;

/**
 *  蓄水池抽样算法
 *  讲解： https://www.jianshu.com/p/7a9ea6ece2af
 *
 */
public class Main {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,0};
        for (int i =0;i<100;i++) {
            System.out.println(Arrays.toString(getM(arr, 1)));
        }
    }

    /**
     *  从无穷大数组中等概率选m个数
     *
     * @param arr
     * @param m
     * @return
     */
    private static int[] getM(int[] arr,int m) {
        int[] ret = new int[m];
        for (int i = 0;i<m;i++) {
            ret[i] = arr[i];
        }
        for (int i = m;i<arr.length;i++) {
            int random = (int)((i+1) * Math.random());
            if (random < m) {
                ret[random] = arr[i];
            }
        }
        return ret;
    }


}


