package com.sankuai.backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//没有写完证，需要加验证条件
public class Leetcode_cn_93 {

    public static void main(String[] args) {
        Leetcode_cn_93 leetcode_cn_93 =new Leetcode_cn_93();
        leetcode_cn_93.restoreIpAddresses("123456");
        System.out.println(Arrays.toString(leetcode_cn_93.results.toArray()));

    }

    private List<String> results = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        if (s.length() > 12 || s.length() < 4){
            return new ArrayList<>();
        }
        backtrace(s,0,0);
        return results;

    }

    private void backtrace(String s, int startIndex, int dotNum) {
        if (dotNum ==3){
            results.add(s);
            return;
        }

        for(int i = startIndex; i<s.length(); i++){
            s= s.substring(0,i+1) + "." + s.substring(i+1);
            backtrace(s,i+2,++dotNum);
            s= s.substring(0,i+1)  + s.substring(i+2);
            --dotNum;
        }
    }

}
