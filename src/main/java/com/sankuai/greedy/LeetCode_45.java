package com.sankuai.greedy;

public class LeetCode_45 {
    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        System.out.println(jump(nums));

        //能跳到终点
        int[] nums_1 = {2,3,0,1,4};
        System.out.println(canJump(nums_1));

        //不能跳到终点
        int[] nums_2 = {2,1,0,1,4};
        System.out.println(canJump(nums_2));

    }

    public static  int jump(int[] A) {
        int jumps = 0, curEnd = 0, curFarthest = 0;
        for (int i = 0; i < A.length - 1; i++) {
            curFarthest = Math.max(curFarthest, i + A[i]);
            if (i == curEnd) {
                jumps++;
                curEnd = curFarthest;
                if (curEnd >= A.length - 1) {
                    break;
                }
            }

        }
        return jumps;
    }

    public static boolean canJump(int[] arr) {
        int maxJump = 0;
        for (int i = 0; i<arr.length;i++) {
            maxJump = Math.max(maxJump,i+arr[i]);
            if (i == maxJump && arr[i] <= 0 && i != arr.length -1) {
                return false;
            }

        }
        return true;
    }

    public static boolean canJumpV2(int[] arr) {
        int flag = arr.length - 1;
        for (int i = arr.length-1; i>=0;i--) {
            if (i + arr[i] >=flag) {
                flag = i;
            }
        }
        return flag ==0;
    }

    //跳格子游戏  是否可以跳到终点  LeetCode55
    public  static boolean canJumpV3(int[] nums) {
        int dis = 0;
        for (int i = 0; i <= dis; i++) {
            dis = Math.max(dis, i + nums[i]);
            if (dis >= nums.length-1) {
                return true;
            }
        }
        return false;
    }
}
