package com.sankuai.disjoint_set_union;

/**
 * 并查集基本实现
 */
public class Basic {
    int[] parent;

    public void init(int n){
        parent = new int[n];
        for(int i=0;i<n;i++){
            parent[i] = i;
        }
    }

    public int find(int x){
        if(parent[x] !=x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x,int y){
        parent[find(x)] = find(y);

    }

    public boolean isOneGroup(int x,int y){
        return find(x) == find(y);
    }


    public static void main(String[] args) {
        int n =6;
        Basic basic = new Basic();
        basic.init(n);
        basic.union(2,3);
        basic.union(3,5);
        System.out.println(basic.isOneGroup(2,5));
    }
}
