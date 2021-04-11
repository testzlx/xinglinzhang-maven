package com.sankuai.leetcode;

public class Leetcode44 {
    public static void main(String[] args) {
        String s = "abcd";
        String pattern = "*cd";
        System.out.println(isMatch(s,pattern));

    }

    public static boolean isMatch(String str, String pattern) {
        int s = 0, p = 0 , startIdx=-1,match =0 ;
        while (s < str.length() ) {
            if(p < pattern.length() && (pattern.charAt(p) == '?' ||
                    str.charAt(s) == pattern.charAt(p)) ) {
                ++s;
                ++p;
            } else if(p < pattern.length() && pattern.charAt(p) == '*') {
                startIdx = p;
                match = s;
                p++;
            }else if (startIdx != -1) {
                ++match;
                s=match;
                p=startIdx+1;
            } else {
                return false;
            }

        }
        while (p < pattern.length()&& pattern.charAt(p) == '*'){
            p++;
        }
        return p == pattern.length();
    }
}
