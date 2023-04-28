package com.sankuai.string;

import java.util.HashSet;
import java.util.Set;

public class Leetcode_cn_1044 {

    final static int P=1313131;
    long[] hash,base;

    //思想： 二分查找【最大重复字符串的子串也满足条件】+字符串hash
    public String longestDupSubstring(String s) {
        String ans= "";
        if(s == null || s.length() ==0){
            return "";
        }
        int len = s.length();
        hash = new long[len+1];
        base = new long[len+1];
        base[0] =1;
        for(int i=0;i<len;i++){
            hash[i+1] = hash[i] * P + s.charAt(i);
            base[i+1] = base[i] * P;
        }
        int l =0,r = len-1;
        while(l < r) {
            int mid = (l+r+1) >>1;
            String tmp = check(s,mid);
            if (tmp != "") {
                l = mid+1;
            }else{
                r = mid-1;
            }
            ans = ans.length()< tmp.length() ? tmp:ans;
        }
        return ans;
    }

    private String check(String s, int len) {
        Set<Long> set = new HashSet<>();
        int end = s.length() - len + 1; // 最后一个区间的位置
        for(int i = 1; i <= end; i++){
            int j = i + len - 1;
            long h = hash[j] - hash[i-1] * base[j-i+1]; // 区间hash值公式
            if(!set.add(h)) return s.substring(i-1,j); // 如果重复 返回该子串
        }
        return "";
    }

    public static void main(String[] args) {
        Leetcode_cn_1044 leetcode_cn_1044 = new Leetcode_cn_1044();
        String s = "banana";
        System.out.println(leetcode_cn_1044.longestDupSubstring(s));
    }
}
