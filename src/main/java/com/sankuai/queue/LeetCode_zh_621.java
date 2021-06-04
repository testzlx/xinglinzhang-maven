package com.sankuai.queue;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LeetCode_zh_621 {
    public static void main(String[] args) {
        char[] tasks = {'A','A','A','B','B','B'};
        System.out.println(leastInterval(tasks,2));

    }

    public static  int leastInterval(char[] tasks, int n) {
        int maxExec = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : tasks) {
            int count = map.putIfAbsent(ch, 0);
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
}
