package com.sankuai.memoization;

public class Leetcode_zh_329 {
    int[][] dist = {{0,1},{1,0},{0,-1},{-1,0}};
    int m,n;
    public static void main(String[] args) {

    }

    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        int help[][] = new int[m][n];
        int ans = Integer.MIN_VALUE;
        for (int i = 0;i < m;i++) {
            for(int j = 0;j < n;j++) {
                ans = Math.max(ans,dfs(matrix,i,j,help));
            }
        }
        return ans;
    }

    private int dfs(int[][] matrix, int i, int j, int[][] help) {
        if (help[i][j] != 0) {
            return help[i][j];
        }
        help[i][j]++;
        for (int[] tmp : dist) {
            int newRow = i + tmp[0], newCol = j + tmp[1];
            if (newRow >= 0 && newRow < m  && newCol >= 0 && newCol < n  && matrix[i][j] < matrix[newRow][newCol]) {
                help[i][j] = Math.max(help[i][j],dfs(matrix,newRow,newCol,help) +1);
            }
        }
        return help[i][j];
    }
}
