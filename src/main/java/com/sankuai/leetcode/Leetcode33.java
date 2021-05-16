package com.sankuai.leetcode;

public class Leetcode33 {
    public static void main(String[] args) {
        Leetcode33 leetcode33 = new Leetcode33();
        int[] nums= {4,5,6,7,0,1,2};
        int target  = -2;
        System.out.println(leetcode33.search(nums,target));
    }

    public int search(int[] nums, int target) {
            return search(nums,0,nums.length-1,target);
    }
    //第二种解法
    public int searchV2(int[] nums, int target) {
        if(nums==null || nums.length==0) return -1;
        int l=0, r=nums.length-1, m=0;
        // find out the index of the smallest element.
        while(l<r){
            m = (l+r)/2;
            if(nums[m] > nums[r]){
                l = m+1;
            }else{
                r = m;
            }
        }

        // since we now know the start, find out if the target is to left or right of start in the array.
        int s = l;
        l = 0; r = nums.length-1;
        if(target >= nums[s] && target <= nums[r]){
            l = s;
        }else{
            r = s;
        }
        // the regular search.
        while(l<=r){
            m = (l+r)/2;
            if(nums[m]==target) return m;
            else if(nums[m] > target) r = m-1;
            else l=m+1;
        }

        return -1;
    }

    //第三种解法
    public int searchV3(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int right = n-1;
        int mid = 0;

        while(left <= right){

            mid = left+(right-left)/2;


            if(nums[mid] == target)
                return mid;

                // search in left sorted array
            else if(nums[mid] >= nums[left]){
                if(target <= nums[mid] && target >= nums[left])
                    right = mid-1;
                else
                    left = mid+1;
            }else{
                // search in right sorted array
                if(target >= nums[mid] && target <= nums[right])
                    left = mid+1;
                else
                    right = mid-1;
            }
        }
        return -1;
    }

    private int search(int[] nums, int left, int right, int target) {
        if (left < nums.length -1 && nums[left] == target) {
            return left;
        }
        if (left <= right) {
            int mid = left + (right -left)/2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] >= nums[left]) {
                if (target >= nums[left] && target <= nums[mid]) {
                    return search(nums,left,mid-1,target);
                }else {
                    return search(nums,mid+1,right,target);
                }
            }else {
                if (target >= nums[mid] && target <= nums[right]) {
                    return search(nums,mid+1,right,target);
                }else {
                    return search(nums,left,mid-1,target);
                }
            }
        }
        return -1;
    }
}
