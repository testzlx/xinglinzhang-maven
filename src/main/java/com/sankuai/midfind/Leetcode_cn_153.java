package com.sankuai.midfind;


public class Leetcode_cn_153 {

    //部分有序的数组[元素不重复]使用二分查找算法
    public int findMin(int[] nums) {
        int left = 0,right = nums.length-1;
        while(left < right){
            int mid = (left+right)/2;
            if (nums[mid] <nums[right]){
                right = mid;
            }else {
                left =mid+1;
            }
        }
        return left;
    }

    //leetcode_cn_154 部分有序的数组[元素重复]使用二分查找算法
    public int findMinV2(int[] nums) {
        int left = 0,right = nums.length-1;
        while(left < right){
            int mid =left+ (right-left)/2;
            if (nums[mid] <nums[right]){
                right = mid;
            }else if(nums[mid] > nums[left]){
                left =mid+1;
            }else{
                right--;
            }
        }
        return nums[left];
    }

    //  leetcode_cn_81 搜索旋转排序数组 II
    public boolean search(int[] nums, int target) {
        int l = 0, r = nums.length-1;
        while(l<=r){
            //将左右两边重复的元素 进行筛选 保证left 和 right和其前面的不相等，只有这样，我们才能通过mid和right / left的比较，
            // 判断 出 ”哪边是有序的“，然后可以根据target 进行很好的二分
            while(l<r&&nums[l]==nums[l+1]) ++l;
            while(l<r&&nums[r]==nums[r-1]) --r;
            int mid = l+(r-l)/2;
            if(nums[mid]==target) return true;
            //左半部分有序
            if(nums[mid]>=nums[l]){
                if(target<nums[mid]&&target>=nums[l]) r = mid-1;//target落在左半边
                else l = mid+1;
            }
            else{//右半部分有序
                if(target>nums[mid]&&target<=nums[r]) l = mid+1;//target落在右半边
                else r = mid-1;
            }
        }
        return false;
    }



        public static void main(String[] args) {
        int[] arr ={7,8,9,10,1,2,3,6};
            Leetcode_cn_153 leetcode_cn_153 = new Leetcode_cn_153();
            System.out.println(leetcode_cn_153.findMin(arr));
            int[] arr2 ={2,0,2,2,2,2};
            System.out.println(leetcode_cn_153.findMinV2(arr2));
            int[] arr3= {1,0,1,1,1};
            int target = 0;
            System.out.println(leetcode_cn_153.search(arr3,target));

    }
}
