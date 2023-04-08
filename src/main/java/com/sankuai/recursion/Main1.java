package com.sankuai.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main1 {

    public static void main(String[] args) {
        String s = "a1b2";
        System.out.println(Arrays.toString(letterCasePermutation(s).toArray()));
    }

    //属于递归法 https://leetcode.com/problems/letter-case-permutation/discuss/115485/Java-Easy-BFS-DFS-solution-with-explanation
    //把一字符串变换大小写并把所有情况输出
    //Examples:
    //Input: S = "a1b2"
    //Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
    private static List<String> letterCasePermutation(String s){
        List<String> lists = new ArrayList<String>();
        helper(s.toCharArray(),0,lists);
        return lists;
    }

    private static void helper(char[] chars, int pos, List<String> lists) {
        if(pos == chars.length ){
            lists.add(new String(chars));
            return;
        }
        if(chars[pos] >= '0' && chars[pos] <= '9'){
            helper(chars,pos+1,lists);
            return;
        }
        chars[pos] =  Character.toLowerCase(chars[pos]);
        helper(chars,pos+1,lists);
        chars[pos] =  Character.toUpperCase(chars[pos]);
        helper(chars,pos+1,lists);
    }



}
