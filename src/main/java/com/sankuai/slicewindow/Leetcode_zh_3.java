package com.sankuai.slicewindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *  无重复字符的最长子串
 *
 */
public class Leetcode_zh_3 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("abcbcdeba"));
    }

    public static int lengthOfLongestSubstring(String s) {
        int ans= 0 ,end = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0;i < s.length();i++) {
            if (i !=0) {
                set.remove(s.charAt(i -1));
            }

            while(end < s.length() && !set.contains(s.charAt(end))) {
                set.add(s.charAt(end));
                end++;
            }
            if (end -i > ans ) {
                System.out.println(s.substring(i,end));
            }
            ans = Math.max(ans,end -i );

        }
        return ans;
    }

    public static int lengthOfLongestSubstringV2(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0, start = 0;
        for (int end = 0; end < s.length(); end++) {
            char ch = s.charAt(end);
            if (map.containsKey(ch)){
                start = Math.max(map.get(ch)+1,start);
            }
            max = Math.max(max,end - start + 1);
            map.put(ch,end);
        }
        return max;
    }
}
