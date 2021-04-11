package com.sankuai.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Leetcode6 {
    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING",3));
    }

    private static  String convert(String str, int row) {
        if (row ==1 || str.length() <= row){
            return str;
        }
        List<StringBuilder> list = new ArrayList<StringBuilder>();
        for (int i = 0 ;i < row ; i++) {
            list.add(new StringBuilder());
        }
        int currow = 0;
        boolean upOrDown  = false ; // false:down, true:up
        for (char ch : str.toCharArray()){
            list.get(currow).append(ch);
            if (currow == 0 || currow == row -1) {
                upOrDown = !upOrDown;
            }
            currow += upOrDown ? 1 : -1;
        }
        StringBuilder sb = new StringBuilder();
        for(StringBuilder tmp : list) {
            sb.append(tmp);
        }
        return sb.toString();
    }
}
