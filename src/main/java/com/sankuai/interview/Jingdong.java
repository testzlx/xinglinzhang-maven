package com.sankuai.interview;

public class Jingdong {
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
