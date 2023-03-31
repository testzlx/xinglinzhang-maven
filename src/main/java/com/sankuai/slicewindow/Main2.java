package com.sankuai.slicewindow;


import java.util.HashMap;
import java.util.Map;

public class Main2 {
    public static void main(String[] args) {
        String str = "XDOYEZODEYXNZ";
        String target = "XYZ";
        Main2 main2 = new Main2();
        System.out.println( main2.minWindow(str,target));

    }

    public boolean judgeArray (int[] arr) {
        Map<Integer, Boolean> map = new HashMap<>();
        for (int i= 0 ;i<arr.length ;i++) {
            if (map.containsKey(arr[i])) {
                return false;
            }
            map.put(arr[i],true);
        }
        return true;
    }

    public String minWindow (String str, String target) {
        // write code here
        int start =0,end = 0, min = str.length();
        String ret  = null;
        //char[] arr= target.toCharArray();
        for (int i= 0 ;i<str.length();i++) {
            for (int j = i;j< str.length();j++) {
                if (pending(str,i,j,target)) {
                    if (j - i < min) {
                        min = j - i;
                        ret = str.substring(i,j+1);
                    }
                }
            }
        }
        return ret;
    }

    private boolean pending(String str, int i, int j, String target) {
        if (target.indexOf(str.charAt(i)) == -1 || target.indexOf(str.charAt(j)) == -1  ) {
            return false;
        }
        int[] index = new int[target.length()];
        for (int k = i ;k <= j;k++) {
            char ch = str.charAt(k);
            int idx = target.indexOf(ch);
            if (idx != -1) {
                index[idx] = 1;
            }
        }
        for (int ii = 0 ; ii<index.length;ii++) {
            if (index[ii] == 0) {
                return false;
            }
        }
        return true;
    }

}
