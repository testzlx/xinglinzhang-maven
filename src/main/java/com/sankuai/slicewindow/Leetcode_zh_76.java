package com.sankuai.slicewindow;

/**
 *  没看懂
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
}
