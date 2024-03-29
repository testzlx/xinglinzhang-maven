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

    //  数组相对排序,https://leetcode.cn/problems/0H97ZC/
    public static int[]  relativeSortArray(int[] arr1, int[] arr2) {
        int[] newArray = new int[arr1.length];
        int[] tmp = new int[1001];
        int idx = 0;
        for(int i:arr1){
            ++tmp[i];
        }
        for(int i:arr2){
            while(tmp[i]-- >0){
                newArray[idx++] = i;
            }
        }
        for(int i =0;i<=1000;i++){
            while(tmp[i]-->0){
                newArray[idx++] = i;
            }
        }
        return newArray;

    }

    //二维数组顺时针打印
    // https://leetcode.cn/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/
    public static int[] spiralOrder(int[][] matrix) {
        if (matrix == null ||  matrix.length ==0) {
            return new int[0];
        }
        int width = matrix.length;
        int length = matrix[0].length;
        int left =0,top = 0,right = length-1,bottom = width-1,index = 0;
        int[] res = new int[width * length];
        while(true){
            for(int i= left;i<=right;i++){
                res[index++] = matrix[top][i];
            }
            if(++top>bottom){
                break;
            }
            for(int i=top;i<=bottom;i++){
                res[index++] = matrix[i][right];
            }
            if(--right < left){
                break;

            }
            for(int i=right;i>=left;i--){
                res[index++] = matrix[bottom][i];
            }
            if(--bottom >top){
                break;
            }
            for(int i=bottom;i>=top;i--){
                res[index++] = matrix[i][left];
            }
            if(++left > right){
                break;
            }
        }
    return res;
    }


    public static void main(String[] args) {
        int[][] arr ={{1,3},{2,6},{8,10},{15,18}};
        int[][] results = merge(arr);
        System.out.println(123);

        int[] arr1 = {2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = {2,1,4,3,9,6};
        int[] ans = relativeSortArray(arr1,arr2);
        System.out.println(Arrays.toString(ans));


        int[][] doubleArr = {{1,2,3,4},{4,5,6,7},{7,8,9,10}};
        System.out.println(Arrays.toString(spiralOrder(doubleArr)));

    }
}
