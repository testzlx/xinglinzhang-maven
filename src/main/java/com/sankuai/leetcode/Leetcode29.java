package com.sankuai.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Leetcode29 {
    public static void main(String[] args) {
        Leetcode29 leetcode29 = new Leetcode29();
        System.out.println(leetcode29.divide(21,4));
    }

    // Can specify the exact half value here as well instead of using the divide symbol
    int HALF_INT_MIN = Integer.MIN_VALUE/2;

    public int divide(int dividend, int divisor) {

        // Satisfying the overflow condition as specified in the problem
        if (dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;

        // Determining if the output result should be positive or negative;
        boolean isResultNegative = (divisor < 0) ^ (dividend < 0);

        // Converting dividend and divisor to negative values to satisfy the overflow boundary conditions
        dividend = dividend > 0 ? -dividend : dividend;
        divisor = divisor > 0 ? -divisor : divisor;

        // Defining 2 arrays, one for the powers of 2 that reaches upto the quotient and the other one the doubles of divisor
        List<Integer> powersOf2 = new ArrayList<>();
        List<Integer> doublesOfDivisor = new ArrayList<>();

        // Populating the 2 arrays
        int powerOf2 = 1;
        while(dividend <= divisor){
            powersOf2.add(powerOf2);
            doublesOfDivisor.add(divisor);
            // This condition check is for overflow condition check
            if (divisor < HALF_INT_MIN)
                break;
            powerOf2 += powerOf2;
            divisor += divisor;
        }

        // Now we can iterate through the multiple of divisor list to calculate the quotient -
        // The quotient of current dividend is the respective power of 2 (number when multiplied with original divisor).
        // Some part of remainder can again be part of the quotient so we reduce dividend by subtracting it from the corresponding multiple of divisor
        int quotient = 0;
        for(int i=doublesOfDivisor.size()-1; i >= 0 ;i--){
            if(dividend <= doublesOfDivisor.get(i)){
                quotient += powersOf2.get(i);
                dividend -= doublesOfDivisor.get(i);
            }
        }

        return isResultNegative ? -quotient : quotient;
    }
}
