package com.sankuai.leetcode;

public class Leetcode5 {
    public static void main(String[] args) {
        Leetcode5 leetcode5 = new Leetcode5();
        System.out.println(leetcode5.longestPalindrome("abcbaed"));
    }

    public String longestPalindrome(String s) {
        if(s.length() <2){
            return s;
        }
        int n = s.length();
        String res = null;

        boolean[][] dp = new boolean[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);

                if (dp[i][j] && (res == null || j - i + 1 > res.length())) {
                    res = s.substring(i, j + 1);
                }
            }
        }

        return res;
    }
}
