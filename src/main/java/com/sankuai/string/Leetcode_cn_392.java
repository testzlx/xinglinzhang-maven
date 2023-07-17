package com.sankuai.string;

public class Leetcode_cn_392 {

    public static void main(String[] args) {
        System.out.println(isSubsequence("bcd","uuuuuubcd"));
    }
    public static boolean isSubsequence(String s, String t) {
        int i=0,j =0;
        while(i<s.length() && j<t.length()){
            if(s.charAt(i) == t.charAt(j)){
                i++;
                j++;
            }else {
                j++;
            }
        }
        return i == s.length();

    }


    //高级思路， 对t预处理后， 后续的n多判断s不再需要遍历t了
    public static boolean isSubsequenceV2(String s, String t) {
        int n = s.length(),m = t.length();
        int[][] help = new int[m+1][26];
        for(int i = 0;i<26;i++){
            help[m][i] = m;
        }
        for(int i = m-1;i>=0;i--){
            for(int j=0;j<26;j++){
                if(t.charAt(i) - 'a' == j ){
                    help[i][j] = i;
                }else{
                    help[i][j] = help[i+1][j];
                }
            }
        }
        int begin = 0;
        for(int k = 0; k<n;k++){
            if(help[begin][s.charAt(k) - 'a'] == m){
                return false;
            }
            begin = help[begin][k]+1;
        }

    return true;
    }

    //leetcode_cn_395 至少有 K 个重复字符的最长子串
    public int longestSubstring(String s, int k) {
        int[] help = new int[26];
        for(int i =0;i<s.length();i++){
            help[s.charAt(i) -'a'] ++;
        }
        for(int i =0;i<s.length();i++){
            if(help[s.charAt(i)-'a'] <k){
                return Math.max(longestSubstring(s.substring(0,i),k),
                        longestSubstring(s.substring(i+1,s.length()),k));
            }
        }
        return s.length();

    }

}
