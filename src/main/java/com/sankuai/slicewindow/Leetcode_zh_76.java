package com.sankuai.slicewindow;

/**
 *  没看懂
 *  20210603 看懂50%
 */
public class Leetcode_zh_76 {
    public static void main(String[] args) {
        String str = "abdabbcdabc";
        String target = "abc";
        System.out.println(minWindow(str,target));
    }

    public static String minWindow(String s, String t) {
        int[] help = new int[128];
        for (char ch : t.toCharArray()) help[ch]++;
        int left = 0, right = 0, minSize = Integer.MAX_VALUE;
        String res = "";
        int count = t.length();
        while (right < s.length()) {
            char ch = s.charAt(right);
            if (help[ch] > 0) count--;
            help[ch]--;
            while (count == 0) { //如果移动到了合适的窗口
                if (right - left + 1 < minSize) {
                    minSize = right - left + 1;
                    res = s.substring(left, right + 1);//更新结果
                }
                ch = s.charAt(left);
                if (help[ch] == 0) count++;
                help[ch]++;
                left++; //缩小窗口直至count!=0
            }
            right++;
        }
        return res;
    }

    public String minWindowV2 (String str, String target) {
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
