package com.sankuai.disjoint_set_union;

public class Leetcode_cn_547 {

    public int findCircleNum(int[][] isConnected) {
        int cities = isConnected.length;
        int[] parent = new int[cities];
        for (int i = 0; i < cities; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < cities; i++) {
            for (int j = i + 1; j < cities; j++) {
                if (isConnected[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }
        int provinces = 0;
        for (int i = 0; i < cities; i++) {
            if (parent[i] == i) {
                provinces++;
            }
        }
        return provinces;
    }

    public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    public int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }

    //leetcode_cn_648 冗余连接

    public int[] findRedundantConnection(int[][] edges) {
            int n = edges.length;
            int[] parent = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }
            for (int i = 0; i < n; i++) {
                int[] edge = edges[i];
                int node1 = edge[0], node2 = edge[1];
                if (find(parent, node1) != find(parent, node2)) {
                    union(parent, node1, node2);
                } else {
                    return edge;
                }
            }
            return new int[0];
        }

}
