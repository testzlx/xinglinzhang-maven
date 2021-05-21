package com.sankuai.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *  求字符串最长不重复字串长度
 *  leetcode 不知道那一道题
 *
 */
public class LeetcodeN1 {
    public static void main(String[] args) {
        LeetcodeN1 leetcodeN1 = new LeetcodeN1();
        System.out.println( leetcodeN1.maxLength("abcabcbb"));
        System.out.println( leetcodeN1.maxLength("abcbedb"));
        System.out.println( leetcodeN1.maxLengthV2("abcabcbb"));
        System.out.println( leetcodeN1.maxLengthV2("abcbedb"));
    }

    private  int maxLength(String s) {
        int max = 0,begin = 0;
        Map<Character,Integer> hMap = new HashMap<>();
        for(int i = 0 ; i<s.length();i++) {
            char ch = s.charAt(i);
            Integer index = hMap.get(ch);
            if ( index != null) {
                begin = index +1;
            }
            hMap.put(ch,i);
            if(i - begin + 1 > max) {
                max = i - begin + 1;
            }
        }
        return max;
    }

    /**
     *  常规方法，效率低
     *
     * @param s
     * @return
     */
    private int maxLengthV2(String s) {
        char[] arr = s.toCharArray();
        int max = 0;
        for(int i = 0;i<arr.length;i++) {
            Set<Character> set = new HashSet<>();
            for(int j = i+1; j<arr.length;j++) {
                if (set.contains(arr[j])) {
                    if(set.size() > max) {
                        max = set.size();
                        break;
                    }
                }
                set.add(arr[j]);
                if(set.size() > max) {
                    max = set.size();
                }
            }
        }
        return max;
    }

}
