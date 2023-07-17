package com.sankuai.string;

public class Leetcode_cn_299 {

    public String getHint(String secret, String guess) {
        int[] arr = new int[10];
        int countA  = 0 ,countB = 0;
        for(int i = 0 ;i<secret.length();i++){
            if(secret.charAt(i) == guess.charAt(i)){
                ++countA;
            }else {
                if(arr[secret.charAt(i)]-- > 0){
                    ++countB;
                }
                if(arr[guess.charAt(i)]++ < 0){
                    ++countB;
                }
            }
        }
        return countA+"A"+countB+"B";
    }
}
