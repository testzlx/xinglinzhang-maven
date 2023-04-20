package com.sankuai.hard;

import java.util.*;


//很复杂，没看懂
public class Leetcode_cn_218 {

    public List<List<Integer>> getSkyline(int[][] buildings) {
        //类似扫描线的思路
        int n = buildings.length;
        //预处理所有的线
        List<int[]> segs = new ArrayList<>();
        for (int i = 0; i < n; i++)
        {
            //左线段
            segs.add(new int[]{buildings[i][0], buildings[i][2], 1});
            //右线段
            segs.add(new int[]{buildings[i][1], buildings[i][2], -1});
        }

        //按照x排序，x相同 按照原左右顺序排序
        //为了防止优先队列中存在相同x的天际线点
        //如果x相同，都是左端点，将高度高的排在前，反之将高度高的排在后面
        Collections.sort(segs, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            else if (a[2] != b[2]) return b[2] - a[2];
            else if (a[2] == 1) return b[1] - a[1];
            else return a[1] - b[1];
        });

        //遍历所有的线段
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        //高度为0的天际线一直有贡献
        pq.offer(0);
        List<List<Integer>> ans = new ArrayList<>();
        //上一点天际线的高度
        int prev = 0;
        for (int[] seg : segs) {
            int x = seg[0], h = seg[1], flag = seg[2];
            if (flag == 1) {
                //左边的线，加入优先队列
                pq.offer(h);
            } else if (flag == -1){
                //右边的线，该高度的天际线消失，出优先队列
                pq.remove(h);
            }
            int cur = pq.peek();
            if (cur != prev) {
                List<Integer> list = new ArrayList<>();
                list.add(x);
                list.add(cur);
                ans.add(list);
                prev = cur;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[][] arr ={{2,9,10},
                      {3,7,9},
                      {5,12,12},
                      {15,20,10},
                      {19,24,8}};
        Leetcode_cn_218 leetcode_cn_218 =new Leetcode_cn_218();
        List<List<Integer>> ans = leetcode_cn_218.getSkyline(arr);
        for(List<Integer> tmp :ans) {
            System.out.println(Arrays.toString(tmp.toArray()));
        }

    }
}
