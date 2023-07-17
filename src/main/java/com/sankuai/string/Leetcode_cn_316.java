package com.sankuai.string;

import java.util.Deque;
import java.util.LinkedList;

public class Leetcode_cn_316 {

    public static void main(String[] args) {
        Leetcode_cn_316 leetcode_cn_316 = new Leetcode_cn_316();
        String s = leetcode_cn_316.removeDuplicateLetters("cbacdcbc");
        System.out.println(s);
    }

    public String removeDuplicateLetters(String s) {
        Deque<Character> deque  = new LinkedList<>();
        char[] tmp = s.toCharArray();
        for(int i=0;i<tmp.length;i++){
            char ch = tmp[i];
            if(deque.contains(ch)){
                continue;
            }
            while (!deque.isEmpty() && deque.peekLast() > ch && s.indexOf(deque.peekLast(),i) != -1){
                deque.pollLast();
            }
            deque.addLast(ch);
        }
        char[] chs = new char[deque.size()];
        int i = 0;
        while(!deque.isEmpty()){
            chs[i++] = deque.pollFirst();
        }
        return new String(chs);
    }

}
