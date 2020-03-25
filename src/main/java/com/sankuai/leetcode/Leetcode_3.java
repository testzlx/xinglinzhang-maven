package com.sankuai.leetcode;

import java.util.HashSet;
import java.util.Set;

public class Leetcode_3 {

    public static void main(String[] args) {
        String a = "abcabcbb";
        System.out.println(longestSubString(a));
        String b = "bbbbb";
        System.out.println(longestSubString(b));
        String c = "pwwkew";
        System.out.println(longestSubString(c));

    }

    private static int longestSubString(String str) {
        Set<Character> set = new HashSet<Character>();
        int n = str.length();
        int i =0, j =0, maxSize = 0;
        while (j < n){
            if(!set.contains(str.charAt(j))) {
                set.add(str.charAt(j++));
                maxSize = Math.max(j-i,maxSize);
            } else {
                set.remove(str.charAt(i++));
            }
        }
        return maxSize;
    }
}
