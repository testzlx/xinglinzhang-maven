package com.sankuai.string;

public class Leetcode_415 {

    public static void main(String[] args) {
        Leetcode_415 leetcode_415 = new Leetcode_415();
        System.out.println(leetcode_415.sumOfNumberAndReverse(181));
    }

    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int i = num1.length()-1,j = num2.length()-1,carry = 0;
        while(i>=0 || j >= 0 ){
            if (i >=0){
                carry += num1.charAt(i--) - '0';
            }
            if (j >=0){
                carry += num2.charAt(j--) - '0';
            }
            sb.append(carry %10);
            carry = carry/10;
        }
        return  sb.reverse().toString();
    }

    //leetcode_cn_2443
    public boolean sumOfNumberAndReverse(int num) {
        for(int i = num/2;i<=0;i++){
            if (i + reverse(i) == num){
                return true;
            }
        }
        return false;
    }

    private int reverse(int i) {
        int ans = 0;
        while(i >0){
            ans = ans *10 + i%10;
            i = i/10;
        }
        return ans;
    }


}
