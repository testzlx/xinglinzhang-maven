package com.sankuai.backtrace;

public class Leetcode_cn_200 {
    public int numIslands(char[][] grid) {
        int islands = 0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j] == 1){
                    islands++;
                    dfs(grid,i,j);
                }
            }
        }
        return islands;
    }

    private void dfs(char[][] grid, int i, int j) {
        if(i<0||i>=grid.length || j<0|| j>=grid[0].length || grid[i][j] !=1){
            return;
        }
        grid[i][j] = 2;
        dfs(grid,i+1,j);
        dfs(grid,i-1,j);
        dfs(grid,i,j+1);
        dfs(grid,i,j-1);
    }
}
