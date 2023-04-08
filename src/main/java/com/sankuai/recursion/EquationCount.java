package com.sankuai.recursion;

//整数划分问题
// https://blog.csdn.net/weixin_35909255/article/details/54896972
public class EquationCount {

    static int[][] cache = null;
   static int count(int n,int m){
       if(n ==1 || m==1){
           return 1;
       }else if(m >n){
           return count(n,n);
       }else if(m ==n){
           return 1+count(n,n-1);
       }else{
           return count(n-m,m) + count(n,m-1);
       }
   }

    public static void main(String[] args) {
       int n =6,m =3;
       cache = new int[n+1][m+1];
        System.out.println(count(6,3));
    }
}
