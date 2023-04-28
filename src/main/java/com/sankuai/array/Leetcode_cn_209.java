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

    public static void main(String[] args) {
        Leetcode_cn_209 leetcode_cn_209 = new Leetcode_cn_209();
        int[] nums = {1,4,4};
        int target = 40;
        System.out.println(leetcode_cn_209.minSubArrayLen(target,nums));
    }

}
