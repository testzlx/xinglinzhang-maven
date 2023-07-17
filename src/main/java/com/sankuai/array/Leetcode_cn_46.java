package com.sankuai.array;


//接雨水算法
public class Leetcode_cn_46 {

    public long maxWater (int[] arr) {
        int ans = 0;
        for(int i =1;i<arr.length-1;i++){
            int leftmax = 0,rightmax = 0;
            for(int j =i;j>=0;j--){
                leftmax = Math.max(arr[j],leftmax);
            }
            for(int j =i;j<arr.length;j++){
                rightmax = Math.max(arr[j],rightmax);
            }
            ans+=Math.min(leftmax,rightmax) - arr[i];
        }
        return ans;
    }

    public long maxWater_v2 (int[] arr) {
        int[] leftmaxs= new int[arr.length];
        int[] rightmaxs= new int[arr.length];
        for(int i=1;i<arr.length;i++){
            leftmaxs[i] = Math.max(leftmaxs[i-1],arr[i-1]);
        }
        for(int i=arr.length-2;i>=0;i--){
            rightmaxs[i] = Math.max(rightmaxs[i+1],arr[i+1]);
        }
        long ans =0;
        for(int i=0;i<arr.length;i++){
            int minVal = Math.min(leftmaxs[i],rightmaxs[i]);
            ans += Math.min(minVal -arr[i],0);
        }
        return ans;
    }

    //这个思想难理解
    public long maxWater_v3 (int[] arr) {
        int left=0;
        int right=arr.length-1;
        long ans=0;
        int left_max_height=0;
        int right_max_height=0;
        while (left<right)
        {
            if (arr[left]<arr[right])
            {
                if (arr[left]>left_max_height)
                    left_max_height=arr[left];
                else
                    ans+=left_max_height-arr[left];
                left++;
            }
            else
            {
                if (arr[right]>right_max_height)
                    right_max_height=arr[right];
                else
                    ans+=right_max_height-arr[right];
                right--;
            }
        }

        return ans;

    }


    public static void main(String[] args) {

    }
}
