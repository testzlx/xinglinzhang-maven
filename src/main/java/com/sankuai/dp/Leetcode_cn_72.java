package com.sankuai.dp;

import java.util.List;

public class Leetcode_cn_72 {

    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }

    //leetcode_cn_120
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.size()==1){
            return triangle.get(0).get(0);
        }
        int ans = Integer.MAX_VALUE;
        int m = triangle.size();
        int[][] dp = new int[m][m];
        dp[0][0] = triangle.get(0).get(0);
        for(int i=1;i<m;i++){
            List<Integer> rows = triangle.get(i);
            int n = rows.size();
            for(int j=0;j<n;j++){
                if(j==0){
                    dp[i][j] = dp[i-1][j] + rows.get(j);
                }else if(j == n-1){
                    dp[i][j] = dp[i-1][j-1] + rows.get(j);
                }else{
                    dp[i][j] = Math.min(dp[i-1][j],dp[i-1][j-1])+ rows.get(j);
                }
                if(i == m-1){
                    ans = Math.min(ans,dp[i][j]);
                }
            }
        }
        return ans;

    }



    public static void main(String[] args) {

    }
}
