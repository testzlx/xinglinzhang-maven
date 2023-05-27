package com.sankuai.math;

public class Leetcode_cn_offer_1 {

    // 数值的整数次方
    public static double myPow(double x, int n) {
        if( n ==1){
            return x;
        }else if(n ==-1){
            return 1/x;
        }else if(n == 0){
            return 1;
        }
        double half = myPow(x,n/2);
        double tmp = myPow(x,n%2);
        return half * half * tmp;
    }

    public static void main(String[] args) {
        System.out.println(myPow(3,4));
    }

}
