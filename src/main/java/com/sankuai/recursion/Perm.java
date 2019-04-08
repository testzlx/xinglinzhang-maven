package com.sankuai.recursion;

//排列问题求解
public class Perm {
    private  static void swap(int[] arr,int i,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static  void permSolution(int[] arr,int k,int m){
        if(k ==m){
            for(int i =0;i<m;i++){
                System.out.print(arr[i]);
            }
            System.out.println(arr[m]);
        }else{
            for(int i=k;i<=m;i++){
                swap(arr,i,k);
                permSolution(arr,k+1,m);
                swap(arr,i,k);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {0,1,2,3,4,5,6};
        permSolution(arr,0,6);

    }
}
