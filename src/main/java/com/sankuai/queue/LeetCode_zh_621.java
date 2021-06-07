package com.sankuai.queue;

import java.util.*;

public class LeetCode_zh_621 {
    public static void main(String[] args) {
        char[] tasks = {'A','A','A','B','B','B'};
        System.out.println(leastInterval(tasks,2));
        int[] arr = {3,5,2,6};
        System.out.println(Arrays.toString( mostCompetitive(arr,2)));

    }

    public static  int leastInterval(char[] tasks, int n) {
        int maxExec = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : tasks) {
            int count = map.getOrDefault(ch, 0);
            map.put(ch, ++count);
            maxExec = Math.max(maxExec, count);
        }
        // 具有最多执行次数的任务数量
        int maxCount = 0;
        Set<Map.Entry<Character, Integer>> entrySet = map.entrySet();
        for (Map.Entry<Character, Integer> entry : entrySet) {
            int value = entry.getValue();
            if (value == maxExec) {
                ++maxCount;
            }
        }

        return Math.max((maxExec - 1) * (n + 1) + maxCount, tasks.length);
    }

    /**
     *  1673
     * @param nums
     * @param k
     * @return
     */
    public static int[] mostCompetitive(int[] nums, int k) {
        Stack<Integer> stack = new Stack<>();
        for(int i = 0;i <nums.length;i++) {
            while(!stack.isEmpty()
                    && stack.peek() > nums[i]
                    && k - stack.size() <= nums.length - i -1) {
                stack.pop();
            }
            if (stack.size() < k) {
                stack.push(nums[i]);
            }
        }
        int[] res = new int [k];
        for(int i = k -1;i>=0;i--) {
            res[i] = stack.pop();
        }
        return res;
    }
}
