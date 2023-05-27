package com.sankuai.backtrace;

import java.util.*;

public class Leetcode_cn_131 {

    public static void main(String[] args) {
        String s = "aabba";
        Leetcode_cn_131 leetcode_cn_131 = new Leetcode_cn_131();
        List<List<String>> results = leetcode_cn_131.partition(s);
        for(List<String> list:results){
            System.out.println(Arrays.toString(list.toArray()));
        }

    }

    List<List<String>> lists = new ArrayList<>();
    Deque<String> deque = new LinkedList<>();

    public List<List<String>> partition(String s) {
        backtracing(s,0);
        return lists;
    }

    private void backtracing(String s, int startIndex) {
        if(startIndex >=s.length()){
            lists.add(new ArrayList<>(deque));
            return;
        }

        for(int i = startIndex;i<s.length();i++){
            String tmp = s.substring(startIndex,i+1);
            if(isPalindrome(tmp)){
                deque.add(tmp);
                backtracing(s,i+1);
                deque.pollLast();
            }
        }
    }

    private boolean isPalindrome(String tmp) {
        for(int i = 0;i<=tmp.length()/2;i++){
            if(tmp.charAt(i) != tmp.charAt(tmp.length()-i-1)){
                return false;
            }
        }
        return true;
    }
}
