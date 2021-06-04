package com.sankuai.toposort;

import java.util.*;

public class Leetcode_zh_207 {
    public static void main(String[] args) {
        int[][] prerequisites = {{1,0}};
        System.out.println(findOrder(2,prerequisites));

    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] arr = new int[numCourses];
        Map<Integer,List<Integer>> map = new HashMap<>();
        for (int[] tmp : prerequisites) {
            arr[tmp[0]]++;
            List<Integer> list = map.getOrDefault(tmp[1],new ArrayList<>());
            list.add(tmp[0]);
            map.put(tmp[1],list);
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0;i<numCourses;i++) {
            if (arr[i] ==0) {
                queue.add(i);
            }
        }
        int count = 0;
        while (!queue.isEmpty()) {
            ++count;
            Integer visit = queue.poll();
            List<Integer> list = map.getOrDefault(visit,new ArrayList<>());
            for (Integer val : list) {
                if (--arr[val] == 0) {
                    queue.add(val);
                }
            }
        }
        return count == numCourses;
    }

    //leetcode-210
    public static  int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] arr = new int[numCourses];
        Map<Integer,List<Integer>> map = new HashMap<>();
        for (int[] tmp : prerequisites) {
            arr[tmp[0]]++;
            List<Integer> list = map.getOrDefault(tmp[1],new ArrayList<>());
            list.add(tmp[0]);
            map.put(tmp[1],list);
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0;i<numCourses;i++) {
            if (arr[i] ==0) {
                queue.add(i);
            }
        }
        int count = 0;
        int[] result = new int[numCourses];
        while (!queue.isEmpty()) {
            Integer visit = queue.poll();
            result[count++] = visit;
            List<Integer> list = map.getOrDefault(visit,new ArrayList<>());
            for (Integer val : list) {
                if (--arr[val] == 0) {
                    queue.add(val);
                }
            }
        }
        if (count != numCourses){
            return new int[0];
        }else {
            return result;
        }
    }
}
