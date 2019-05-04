package com.sankuai.algorithm;

public class Main {

    public static void main(String[] args) {
        int a[] = {1, 2, 3, 9};
        int b[] = {5, 6, 11,13};
        double result = findMedianSortedArrays(a, b);
        System.out.println("result:" + result);

        String str = longestPalindrome("babad");
        System.out.println("str:"+str);


        int commonCount = lcs("cnblogs","belong");
        System.out.println("lcs commonCount:" + commonCount);
    }

    //https://leetcode.com/problems/median-of-two-sorted-arrays/solution/
    //找两个数组的中位数
    public static double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A;
            A = B;
            B = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j - 1] > A[i]) {
                iMin = i + 1; // i is too small
            } else if (i > iMin && A[i - 1] > B[j]) {
                iMax = i - 1; // i is too big
            } else { // i is perfect
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = B[j];
                } else if (j == n) {
                    minRight = A[i];
                } else {
                    minRight = Math.min(B[j], A[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    //找出字符串最大回文子串https://leetcode.com/problems/longest-palindromic-substring/  动态规划思想
    public  static String longestPalindrome(String s) {
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
    //找出字符串最大回文子串 递归思想  https://leetcode.com/problems/longest-palindromic-substring/discuss/2928/Very-simple-clean-java-solution
    static int start = 0,maxLen = 0;
    public  static String longestPalindrome2(String s) {
        int size = s.length();
        if(size < 2){
            return s;
        }
        for(int i = 1;i<size ;i++){
            extendPalindrome(s,i,i);
            extendPalindrome(s,i,i+1);
        }
         return s.substring(start, start + maxLen);
    }

    private static void extendPalindrome(String s, int i, int j) {
        while(i >=0 && j<s.length() -1 && s.charAt(i) == s.charAt(j)){
            i--;
            j++;
        }
        if(maxLen <j+1-i ){
            maxLen = j+1-i;
            start = i;
        }
    }

    //求最长公共子序列（LCS  longest common sequences） http://www.cnblogs.com/en-heng/p/3963803.html  动态规划思想
    public static int lcs(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int c[][] = new int[len1+1][len2+1];
        for (int i = 0; i <= len1; i++) {
            for( int j = 0; j <= len2; j++) {
                if(i == 0 || j == 0) {
                    c[i][j] = 0;
                } else if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    c[i][j] = c[i-1][j-1] + 1;
                } else {
                    c[i][j] = Math.max(c[i - 1][j], c[i][j - 1]);
                }
            }
        }
        return c[len1][len2];
    }

}
