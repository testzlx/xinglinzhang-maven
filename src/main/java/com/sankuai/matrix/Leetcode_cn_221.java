package com.sankuai.matrix;

//最大正方形
public class Leetcode_cn_221 {

    public int maximalSquare(char[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;
        if(m == 0){
            return 0;
        }
        int res = 0;
        int[][] dp = new int[m+1][n+1];
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(matrix[i][j] ==1){
                    dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i][j-1],dp[i-1][j])) +1;
                    res = Math.max(res,dp[i][j]);
                }
            }
        }
        return res*res;
    }
}
