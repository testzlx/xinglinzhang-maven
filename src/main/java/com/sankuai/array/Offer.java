package com.sankuai.array;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

//剑指offer数组类型题目
public class Offer {

    //区间合并， https://leetcode.cn/problems/SsGoHC/
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,(o1,o2)-> o1[0]-o2[0]);
        List<int[]> lists = new ArrayList<int[]>();
        int left = -1,right =-1;
        for (int i=0;i<intervals.length;i++) {
            int a = intervals[i][0],b =intervals[i][1];
            if (right ==-1 || a <= right){
                if(left ==-1){
                    left = a;
                }
                right = Math.max(right,b);
            } else {
                int[] items = new int[2];
                items[0] = left;
                items[1] = right;
                lists.add(items);
                left = a;
                right = b;
            }
        }
        if (left!=-1) {
            int[] items = new int[2];
            items[0] = left;
            items[1] = right;
            lists.add(items);
        }
        int[][] tmp = new int[lists.size()][2];
        int j =0;
        for(Iterator<int[]> it=lists.iterator();it.hasNext();){
            int[] list = it.next();
            tmp[j][0] = list[0];
            tmp[j++][1] = list[1];
        }
        return  tmp;
    }


    public static void main(String[] args) {
        int[][] arr ={{1,3},{2,6},{8,10},{15,18}};
        int[][] results = merge(arr);
        System.out.println(123);

    }
}
