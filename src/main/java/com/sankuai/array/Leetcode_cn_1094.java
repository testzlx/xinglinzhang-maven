package com.sankuai.array;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Leetcode_cn_1094 {

    //差分数组思想?
    public boolean carPoolingv1(int[][] trips, int capacity) {
        int[]num = new int[1002];
        for(int[]t:trips){
            num[t[1]]+= t[0];
            num[t[2]] -= t[0];
        }
        int sum = 0;
        for(int i = 0;i < num.length;i++){
            sum += num[i];
            if(sum > capacity){
                return false;
            }
        }
        return true;

    }

    public boolean carPoolingv2(int[][] trips, int capacity) {
        Map<Integer,Integer> hmap = new TreeMap<>();
        for(int[] item:trips){
            hmap.put(item[1],hmap.getOrDefault(item[1],0)+item[0]);
            hmap.put(item[2],hmap.getOrDefault(item[2],0)-item[0]);
        }
        int sum = 0;
        for(Map.Entry<Integer,Integer> entry:hmap.entrySet()){
            sum+= entry.getValue();
            if(sum> capacity){
                return false;
            }
        }
        return true;

    }


    public static void main(String[] args) {

    }
}
