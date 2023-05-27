package com.sankuai.interview;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Baidu {
    public int findKVal(int[] arr,int k){
        quickSort(arr,0,arr.length-1);
        return arr[k];
    }

    public void quickSort(int[] arr,int left, int right){
        if (left <right) {
            int index = partition(arr,left,right);
            quickSort(arr,left,index -1);
            quickSort(arr,index+1,right);
        }

    }

    private int partition(int[] arr, int left, int right) {
        int tmp = arr[left];
        while(left < right){
            while(left < right && arr[right] > tmp){
                right--;
            }
            if(left < right){
                arr[left++] = arr[right];
            }
            while(left < right && arr[left] < tmp){
                left++;
            }
            if(left < right){
                arr[right--] = arr[left];
            }

        }
        arr[left] = tmp;
        return left;
    }


    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
        }
    }


    public List<TreeNode> generateTree(int left, int right){
        List<TreeNode> ret = new ArrayList<>();
        if (left > right){
            ret.add(null);
            return ret;
        }
        for(int i=left;i<right;i++){
            List<TreeNode> leftTrees = generateTree(left,i-1);
            List<TreeNode> rightTrees = generateTree(i+1,right);
            for(TreeNode leftNode:leftTrees){
                for(TreeNode rightNode:rightTrees){
                    TreeNode root = new TreeNode(i);
                    root.left = leftNode;
                    root.right = rightNode;
                    ret.add(root);
                }
            }
        }
        return ret;
    }


    public static void main(String[] args) {
        int[] arr ={4,2,2,5,7};
        Baidu main3 = new Baidu();
        main3.quickSort(arr,0,4);
        System.out.println(Arrays.toString(arr));

    }
}
