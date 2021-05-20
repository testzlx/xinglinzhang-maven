package com.sankuai.algorithm;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        int a[] = {1, 2, 3, 9};
        int b[] = {5, 6, 11,13};
        double result = findMedianSortedArrays(a, b);
        System.out.println("result:" + result);

        List<String> letters =  letterCasePermutation2("abc");
        System.out.println("letter:"+letters);
        char[] chars = {'C','C','C','D','D','D'};
        int count = leastInterval(chars,2);
        System.out.println("leastInterval , count: "+ count);
    }

    //https://leetcode.com/problems/median-of-two-sorted-arrays/solution/
    //找两个数组的中位数，HARD模式,不看
    public static double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A;
            A = B;
            B = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j - 1] > A[i]) {
                iMin = i + 1; // i is too small
            } else if (i > iMin && A[i - 1] > B[j]) {
                iMax = i - 1; // i is too big
            } else { // i is perfect
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = B[j];
                } else if (j == n) {
                    minRight = A[i];
                } else {
                    minRight = Math.min(B[j], A[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }






    //走迷宫，上下左右可以走，同序（顺序，逆序）最大的路径(个人感觉：回溯法 && 动态规划)
    // https://leetcode.com/problems/longest-increasing-path-in-a-matrix/discuss/78308/15ms-Concise-Java-Solution

    static  int dirs[][] = {{0,1},{0,-1},{-1,0},{1,0}};
    private  static int longestIncreasingPath(int[][] matrix){
        int m = matrix.length,n = matrix[0].length ,max = 0;
        int cache[][] = new int[m+1][n+1];
        for(int i =0;i<m;i++){
            for (int j=0;j<n;j++){
                int len = dfs(matrix, i, j, m, n, cache);
                max = Math.max(max, len);
            }
        }
        return max;
    }

    private static int dfs(int[][] matrix, int i, int j, int m, int n, int[][] cache) {
        if(cache[i][j] != 0){
            return cache[i][j];
        }
        int max = 1;
        for(int[] dir : dirs){
            int x = i+dir[0] , y = j+dir[1];
            if(x < 0 || x > m-1 || y< 0 || y>n-1 || matrix[x][y] >matrix[i][j]){
                continue;
            }
            int length = 1 + dfs(matrix, x, y, m, n, cache);
            max = Math.max(max, length);
        }
        cache[i][j] = max;
        return max;
    }

    //把一字符串变换大小写并把所有情况输出(好聪明啊，队列中不会重复元素)
    //广度遍历
    private static List<String> letterCasePermutation2(String s){
        Queue<String> queue  = new LinkedList<String>();
        queue.offer(s);
        for(int i =0 ;i<s.length();i++){
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                continue;
            }
            int size = queue.size();
            for(int j = 0;j< size;j++){
                String cur =   queue.poll();
                char[] chs = cur.toCharArray();

                chs[i] = Character.toUpperCase(chs[i]);
                queue.offer(String.valueOf(chs));

                chs[i] = Character.toLowerCase(chs[i]);
                queue.offer(String.valueOf(chs));
            }
        }
        return new ArrayList<>(queue);
    }


    //https://leetcode.com/problems/task-scheduler/
    //
    public static  int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        Arrays.sort(map);
        int max_val = map[25] - 1, idle_slots = max_val * n;
        for (int i = 24; i >= 0 && map[i] > 0; i--) {
            idle_slots -= Math.min(map[i], max_val);
        }
        return idle_slots > 0 ? idle_slots + tasks.length : tasks.length;
    }




    //贪心算法  nb 跳格子游戏
    public static  boolean canJump2(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }

    //贪心算法  小孩子分蛋糕问题求解 https://leetcode.com/problems/assign-cookies/
     private static  int assignCookies(int[] children,int[] cookies){
         Arrays.sort(children);
         Arrays.sort(cookies);
         int i ,j;
         for(i=0,j=0;i<children.length && j < cookies.length; j++ ){
             if(children[i] <= cookies[j]){
                 i++;
             }
         }
         return i;

     }

}
