package com.sankuai.array;

public class Leetcode_cn_209 {
    public int minSubArrayLen(int target, int[] nums) {
        int sum = 0,j=0,ans = nums.length +1;
        for(int i=0;i<nums.length;i++){
            sum +=nums[i];
            while(sum >= target){
                ans = Math.min(ans,i-j+1);
                sum -=nums[j++];
            }
        }
        ans = ans == nums.length+1 ? 0:ans;
        return ans;
    }

    //思想和以上类似，lastMark:记录前一个前缀和的最后满足位置
    public int minSubArrayLenV2(int target, int[] nums) {
        int[] sums = new int[nums.length];
        sums[0] = nums[0];
        int begin = -1;
        for(int i=0; i<nums.length;i++){
            sums[i] = i==0? nums[0]:sums[i-1] + nums[i];
            if(sums[i] >= target && begin == -1){
                begin = i;
            }
        }
        if (begin == -1){
            return 0;
        }
        int ans = begin+1,lastMark = 0;
        for(int i= begin;i<=nums.length-1;i++){
            for(int j =lastMark;j< i;j++){
                if(sums[i] - sums[j] >= target ){
                    ans = Math.min(ans,i-j);
                    lastMark = j;
                }else{
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Leetcode_cn_209 leetcode_cn_209 = new Leetcode_cn_209();
        int[] nums = {2,3,1,2,4,3};
        int target = 7;
        System.out.println(leetcode_cn_209.minSubArrayLenV2(target,nums));
    }

}
