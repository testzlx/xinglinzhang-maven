package com.sankuai.backtrace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//组合问题，回溯算法
public class Leetcode17 {
    public static void main(String[] args) {
        Leetcode17 leetcode17 = new Leetcode17();
        List<String> list = leetcode17.letterCombinations("457");
        System.out.println(list);
    }
    static Map<Integer, String> map = new HashMap<>();

    static {
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");
    }

    private static void helper(char[] arr, int index, String curr, List<String> list) {

        if (curr.length() == arr.length) {
            list.add(curr);
            return;
        }

        String str = map.get(Character.getNumericValue(arr[index]));
        for (int i = 0; i < str.length(); i++) {
            helper(arr, index + 1, curr + str.charAt(i), list);
        }
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        helper(digits.toCharArray(), 0, "", result);
        return result;
    }
}
