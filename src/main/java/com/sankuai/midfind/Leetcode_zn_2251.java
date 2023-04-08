package com.sankuai.midfind;

import java.util.*;

public class Leetcode_zn_2251 {
    public static int[] fullBloomFlowers(int[][] flowers, int[] persons) {
        List<Integer> start = new ArrayList<>(), end = new ArrayList<>();
        for (int[] flower : flowers) {
            start.add(flower[0]);
            end.add(flower[1]);
        }
        start.sort((o1, o2) -> o1 - o2);
        end.sort((o1, o2) -> o1 - o2);
        int[] res = new int[persons.length];
        for (int i = 0; i < persons.length; ++i) {
            //  二分计算有多少花在 <= persion[i]时刻开放
            int l = 0, r = start.size() - 1;
            int cnt = 0;
            while (l < r) {
                int mid = (r - l + 1) / 2 + l;
                if (start.get(mid) > persons[i]) r = mid - 1;
                else l = mid;
            }
            if (start.get(l) <= persons[i]) cnt += (l + 1);
            // 二分计算有多少花在 < person[i] 到达时候凋谢
            l = 0;
            r = end.size() - 1;
            while (l < r) {
                int mid = (r - l + 1) / 2 + l;
                if (end.get(mid) >= persons[i]) r = mid - 1;
                else l = mid;
            }
            if (end.get(l) < persons[i]) cnt -= (l + 1);

            res[i] = cnt;
        }
        return res;
    }

    //第二种解答， 差分思想
    public static int[] fullBloomFlowers_v2(int[][] flowers, int[] persons) {
        Set<Integer> set = new TreeSet<>();
        for (int[] start_end : flowers) {
            set.add(start_end[0]);
            set.add(start_end[1]);
        }
        Map<Integer, Integer> hmap = new HashMap<>();
        int p = 0;
        for (int tmp : set) {
            hmap.put(tmp, p++);
        }
        int[] count = new int[p + 10];
        for (int[] start_end : flowers) {
            count[hmap.get(start_end[0])]++;
            count[hmap.get(start_end[1])]--;
        }
        for(int i =1;i<=p;i++){
            count[i]+=count[i-1];

        }
        for(int i=0;i<persons.length;i++){
            persons[i]=count[hmap.get(persons[i])];
        }
        return persons;

    }

    public static void main(String[] args) {
        int[][] flowers = {{1, 7}, {2, 8}, {3, 9}, {4, 10}};
        int[] persons = {5};
        int[] result = fullBloomFlowers_v2(flowers, persons);
        System.out.println(Arrays.toString(result));

    }
}
