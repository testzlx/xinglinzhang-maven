package com.sankuai.matrix;

import java.util.ArrayDeque;
import java.util.Deque;

//从左上角走到右下角。 最小路径和
public class Leetcode_cn_64 {

    public static void main(String[] args) {
        /*
        int[][] arr = {{1,3,1},
                        {1,5,1},
                        {4,2,1}};
                        */
        int[][] arr = {{1,2},
                        {1,1}};
        Leetcode_cn_64 leetcode_cn_64 = new Leetcode_cn_64();
        int result = leetcode_cn_64.minPathSum(arr);
        System.out.println(result);
        int[][] arr2 = {{0,0,0},
                {0,1,0},
                {0,0,0}};
        result = leetcode_cn_64.uniquePathsWithObstacles(arr2);
        System.out.println(result);

    }

    int path = Integer.MAX_VALUE;
    int[][] tmp = {{1,0},{0,1}};
    //使用这个超时了，可以跑出正确的结果
    public int minPathSum(int[][] grid) {
        Deque<Integer> stack = new ArrayDeque<>();
        dfs(grid,0,0,stack);
        return path;
    }


    private void dfs(int[][] grid, int i, int j, Deque<Integer> stack) {
        int m = grid.length,n = grid[0].length;
        stack.addLast(grid[i][j]);
        if (i ==m-1 && j ==n-1) {
            path = Math.min(path,sum(stack));
            return;
        }else {
            for (int k=0;k<tmp.length;k++) {
                int newi = i + tmp[k][0];
                int newj = j+tmp[k][1];
                if (newi>=m || newj>=n){
                    continue;
                }
                dfs(grid, newi, newj, stack);
                stack.pollLast();
            }
        }
    }

    private int sum(Deque<Integer> stack) {
        int ans = 0;
        for(int a:stack){
            ans += a;
        }
        return ans;
    }

    //动态规划思想
    public int minPathSumV2(int[][] grid) {
        if (grid == null || grid.length ==0) {
            return 0;
        }
        int m = grid.length ,n =grid[0].length;
        int[][] res = new int[m][n];
        for (int i=1;i<m;i++) {
            res[i][0] = grid[i][0] + res[i-1][0];
        }

        for (int i=1;i<n;i++) {
            res[0][i] = grid[0][i] + res[0][i-1];
        }
        for(int i =1;i<m;i++){
            for(int j=1;j<n;j++){
                res[i][j] = Math.min(res[i-1][j],res[i][j-1])+grid[i][j];
            }
        }
        return res[m-1][n-1];
    }


    //leetcode_cn_63
    public int uniquePathsWithObstacles(int[][] grid) {
        if (grid == null || grid.length ==0) {
            return 0;
        }
        int m = grid.length ,n =grid[0].length;
        int[][] res = new int[m][n];
        if(grid[0][0] == 1){
            return 0;
        }
        res[0][0] =1;
        for (int i=1;i<m;i++) {
            if(grid[i][0] == 1 || res[i-1][0] == 0 ) {
                res[i][0] = 0;
            }else {
                res[i][0] = 1;
            }
        }

        for (int i=1;i<n;i++) {
            if(grid[0][i] == 1 || res[0][i-1] == 0 ) {
                res[0][i] = 0;
            }else {
                res[0][i] = 1;
            }
        }
        for(int i =1;i<m;i++){
            for(int j=1;j<n;j++){
                if (grid[i][j] ==1) {
                    res[i][j] =0;
                }else {
                    res[i][j] = res[i-1][j]+res[i][j-1];
                }
            }
        }
        return res[m-1][n-1];

    }
}
