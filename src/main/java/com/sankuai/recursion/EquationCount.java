package com.sankuai.recursion;

//整数划分问题
public class EquationCount {

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
        System.out.println(count(6,2));
    }
}
