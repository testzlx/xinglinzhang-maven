package com.sankuai.random;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *  没看懂
 *
 */
public class Leetcode_zh_519 {
    public static void main(String[] args) {
        Leetcode_zh_519 leetcode_zh_519 = new Leetcode_zh_519(100,100);
        System.out.println( Arrays.toString(leetcode_zh_519.flip()));
        System.out.println(Arrays.toString(leetcode_zh_519.flip()));
        System.out.println(Arrays.toString(leetcode_zh_519.flip()));
        System.out.println(Arrays.toString(leetcode_zh_519.flip()));
    }

    Map<Integer, Integer> V = new HashMap<>();
    int nr, nc, rem;
    Random rand = new Random();

    public Leetcode_zh_519(int n_rows, int n_cols) {
        nr = n_rows;
        nc = n_cols;
        rem = nr * nc;
    }

    public int[] flip() {
        int r = rand.nextInt(rem--);
        int x = V.getOrDefault(r, r);
        V.put(r, V.getOrDefault(rem, rem));
        return new int[]{x / nc, x % nc};
    }

    public void reset() {
        V.clear();
        rem = nr * nc;

    }
}
