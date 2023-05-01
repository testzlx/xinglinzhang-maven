package com.sankuai.memorysearch;

import java.util.Arrays;
import java.util.List;

public class Leetcode_cn_139 {

    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp =new boolean[len+1];
        dp[0] = true;
        for(int i=1;i<=len;i++){
            for(int j=0;j<i;j++){
                if(dp[j] && wordDict.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];


    }

    public static void main(String[] args) {
        List<String> wordDict = Arrays.asList("car","ca","rs");
        String s = "cars";
        Leetcode_cn_139 leetcode_cn_139 = new Leetcode_cn_139();
        System.out.println(leetcode_cn_139.wordBreak(s,wordDict));

    }
}
