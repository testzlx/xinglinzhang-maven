package com.sankuai.dp;

public class Main {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
        int commonCount = lcs("cnblogs","belong");
        System.out.println("lcs commonCount:" + commonCount);
        System.out.println("公共子串len:" + lcsV2("cnblogs","belong"));
        boolean isMatch = isMatch("abc","a*");
        System.out.println("isMatch:" +isMatch);
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

    /* 求最长公共子串 */
    public static int lcsV2(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int result = 0;     //记录最长公共子串长度
        int c[][] = new int[len1+1][len2+1];
        for (int i = 0; i <= len1; i++) {
            for( int j = 0; j <= len2; j++) {
                if(i == 0 || j == 0) {
                    c[i][j] = 0;
                } else if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    c[i][j] = c[i-1][j-1] + 1;
                    result = Math.max(c[i][j], result);
                } else {
                    c[i][j] = 0;
                }
            }
        }
        return result;
    }

    /* 求最长公共子串  二分查找+哈希 */
    public static int lcsV2_v3(String str1, String str2) {
        return 0;
    }


    /* 求最长公共子串  滑动窗口思想 */
    public static int lcsV2_v2(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int ret = 0;
        for(int i=0;i<len1;i++){
            int len = Math.min(len2,len1-i);
            int commonLen = find_len(str1,str2,i,0,len);
            ret = Math.max(ret,commonLen);
        }

        for(int i=0;i<len2;i++){
            int len = Math.min(len1,len2-i);
            int commonLen = find_len(str1,str2,0,i,len);
            ret = Math.max(ret,commonLen);
        }
        return ret;
    }

    private static int find_len(String str1, String str2, int str1_start, int str2_start, int len) {
        int tmp =0,ret = 0;
        for(int i= 0;i<len;i++){
            if(str1.charAt(str1_start+i) == str2.charAt(str2_start+i)){
                tmp++;
            }else{
                tmp = 0;
            }
            ret = Math.max(ret,tmp);
        }
        return ret;
    }

    //leetcode_cn 44 字符串通配符匹配
    public static boolean isMatch(String s, String p) {
        int slen = s.length();
        int plen = p.length();
        int i=0,j=0,start = -1,match=0;
        while(i < slen){
            if (j < plen && ((p.charAt(j)) == '?' || s.charAt(i) ==p.charAt(j) )){
                j++;
                i++;
            }else if (j < plen && p.charAt(j) == '*'){
                match = j;
                start = i;
                j++;
            }else  if(start !=-1){
                i= ++ start;
                j =match+1;
            }else {
                return false;
            }
        }
        while(j<plen){
            if(p.charAt(j) != '*'){
                return false;
            }
            j++;
        }
        return true;

    }
}
