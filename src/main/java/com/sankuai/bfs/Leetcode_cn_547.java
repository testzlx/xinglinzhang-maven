package com.sankuai.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class Leetcode_cn_547 {

    public int findCircleNum(int[][] isConnected) {
        boolean[] visited = new boolean[isConnected.length];
        Queue<Integer> queue = new LinkedList<Integer>();
        int provinces = 0;
        for(int i =0;i<isConnected.length;i++){
            if( !visited[i]){
                queue.offer(i);
                while(!queue.isEmpty()){
                    int k = queue.poll();
                    visited[k] = true;
                    for(int j=0;j<isConnected.length;j++){
                        if(isConnected[k][j] >0 && !visited[j]){
                            queue.offer(j);
                        }
                    }
                }
                provinces++;
            }
        }
        return provinces;
    }
}
