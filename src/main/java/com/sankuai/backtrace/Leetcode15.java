package com.sankuai.backtrace;

import java.util.*;
import java.util.stream.Collectors;


public class Leetcode15 {

    public static void main(String[] args) {
        Leetcode15 leetcode15  = new Leetcode15();
        int[] nums =  {-1,0,1,2,-1,-4};
        List<List<Integer>> results =leetcode15.threeSum(nums);
        for(List<Integer> pair:results){
            System.out.println(Arrays.toString(pair.toArray()));
        }
        System.out.println("-------------------------------------------");
        int[] arr = {1,4,5};
        leetcode15.combinationCount(5,arr);
        for(List<Integer> pair:leetcode15.ans) {
            System.out.println(Arrays.toString(pair.toArray()));
        }
        System.out.println("----------------------V2---------------------");
        leetcode15.combinationCountV2(5,arr);
        for(List<Integer> pair:leetcode15.ans) {
            System.out.println(Arrays.toString(pair.toArray()));
        }


    }
    //也是使用回溯算法，但是新一样的写法
    public List<List<Integer>> combinationCountV2(int target,int[] candidates) {
        ans.clear();
        dfs(0,candidates,new ArrayList<Integer>(),target);
        return ans;
    }

    void dfs(int i,int[] nums,List<Integer> list2,int target){
        if(target<0||i==nums.length) return;
        if(target==0){
            ans.add(new ArrayList<>(list2));
            return;
        }
        list2.add(nums[i]);
        dfs(i,nums,list2,target-nums[i]);
        list2.remove(list2.size()-1);
        dfs(i+1,nums,list2,target);
    }

    //
    public List<List<Integer>> combinationSum3(int k, int n) {
        ans.clear();
        path.clear();
        dfs3(k,n,0,1);
        return ans;
    }

    private void dfs3(int k,int n,int cur_sum,int index){
        if(cur_sum > n){
            return;
        }
        if(path.size() == k){
            if(cur_sum == n){
                ans.add(new ArrayList<>(path));
                return;
            }
        }

        for(int i = index;i<=9;i++){
            path.addLast(i);
            cur_sum += i;
            dfs3(k,n,cur_sum,i+1);
            cur_sum -=i;
            path.pollLast();
        }

    }


    List<List<Integer>> ans=new ArrayList<>();//保存结果
    Deque<Integer> path=new LinkedList<>();//保存单条路径答案

    public void helper(int target,int[] nums,int sum,int start){
        if(target==sum){
            ans.add(new ArrayList<>(path));
            return ;
        }

        for(int i=start;i<nums.length;i++){
            //剪枝，结果和已经大于目标值，无需下一轮递归
            if(sum+nums[i]>target) break;

            path.addLast(nums[i]);
            sum=sum+nums[i];
            helper(target,nums,sum,i);
            sum=sum-nums[i];//回溯
            path.removeLast();//回溯
        }
    }

    //https://leetcode.cn/problems/Ygoe9J
    public List<List<Integer>> combinationCount (int target, int[] nums) {
        // write code here
        if(nums.length==0) return ans;
        Arrays.sort(nums);//排序后便于剪枝
        helper(target,nums,0,0);
        return ans;
    }


    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        int[] arr = new int[3];
        fn(nums,nums.length,0,3,arr,list);
        return list;
    }

    public List<List<Integer>> threeSumV2(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0 ;i<num.length ; i++){
            if (i == 0 || num[i] != num[i-1]) {
                int left = i+1,right = num.length -1, sum = 0 - num[i];
                while (left < right) {
                    if ( num[left] + num[right] == sum) {
                        res.add( Arrays.asList(num[i],num[left],num[right]));
                        while (left < right && num[left] == num[left+1]) {
                            left++;
                        }
                        while (left < right && num[right] == num[right-1]) {
                            right--;
                        }
                        left++;
                        right--;
                    }else if( num[left] + num[right] <sum) {
                        left++;
                    }else {
                        right --;
                    }
                }
            }
        }
        return res;
    }


    /**
     *
     * @param nums 输入
     * @param target 目标和
     * @param size 数组大小
     * @param index n个数字
     * @param arr  临时和
     * @param list 存储结果
     */
    private void fn(int[] nums,int size, int target, int index, int[] arr, List<List<Integer>> list) {
        for (int i = size-1; i>=index-1;i--) {
            arr[index-1] =  nums[i];
            if (index >1) {
                fn(nums,i,target,index-1,arr,list);
            }else {
                if(isSum(arr,target)) {
                    addtoList(list,arr);
                }
            }
        }
    }

    private void addtoList(List<List<Integer>> list, int[] arr) {
        boolean flag = true;
        for(List<Integer> val : list) {
            for(int i : arr) {
                if(!val.contains(i)) {
                    break;
                }
                flag = false;
            }
        }
        if(flag) {
            list.add(Arrays.stream(arr).boxed().collect(Collectors.toList()));
        }
    }

    private static boolean isSum(int[] b,int suM) {
        int sum = 0;
        for(int i:b){
            sum += i;
        }
        if(sum == suM)
            return true;
        return false;
    }
}
