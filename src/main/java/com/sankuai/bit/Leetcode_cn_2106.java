package com.sankuai.bit;

public class Leetcode_cn_2106 {

    static final int N = (int)2e5 + 10;

    int[] bit = new int[N];

    int lowbit(int u) {
        return u & -u;
    }

    void update(int u, int x) {
        for(int i = u; i < N; i += lowbit(i)) bit[i] += x;
    }

    int query(int u) {
        int res = 0;
        for(int i = Math.min(u, N - 1); i > 0; i -= lowbit(i)) res += bit[i];
        return res;
    }

    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        for(int[] f : fruits) update(f[0] + 1, f[1]);
        startPos += 1;
        int res = 0;
        for(int i = 0; i <= k; ++i) {
            res = Math.max(res, query(startPos - i + k - i) - query(startPos - i - 1));
            res = Math.max(res, query(startPos + i) - query(startPos + i - (k - i) - 1));
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
