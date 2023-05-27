package com.visualbusiness.my_test;


public class Main3 {

    public static void main(String[] args) {
        int a = 8;
        System.out.println(find(a));

    }

    public static double find(int num){
        double left = 0,right = num;
        while(true){
            double mid = (left+right) /2;
            double tmp = mid * mid;
            if(Math.abs(tmp-num)< 0.00000001 ){
                return mid;
            }else if (tmp > num){
                right = mid;
            }else {
                left = mid;
            }
        }
    }


}