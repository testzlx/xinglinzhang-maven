package com.sankuai.algorithm;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        int a[] = {1, 2, 3, 9};
        int b[] = {5, 6, 11,13};
        double result = findMedianSortedArrays(a, b);
        System.out.println("result:" + result);

        String str = longestPalindrome("babad");
        System.out.println("str:"+str);


        int commonCount = lcs("cnblogs","belong");
        System.out.println("lcs commonCount:" + commonCount);

        List<String> letters =  letterCasePermutation2("abc");
        System.out.println("letter:"+letters);
        char[] chars = {'C','C','C','D','D','D'};
        int count = leastInterval(chars,2);
        System.out.println("leastInterval , count: "+ count);
        int num[] = {2,3,1,1,4};
        boolean canJump =canJump(num);
        System.out.println("can jump: "+canJump);
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

    //找出字符串最大回文子串https://leetcode.com/problems/longest-palindromic-substring/  动态规划思想
    public  static String longestPalindrome(String s) {
        int n = s.length();
        String res = null;

        boolean[][] dp = new boolean[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);

                if (dp[i][j] && (res == null || j - i + 1 > res.length())) {
                    res = s.substring(i, j + 1);
                }
            }
        }

        return res;
    }
    //找出字符串最大回文子串 递归思想  https://leetcode.com/problems/longest-palindromic-substring/discuss/2928/Very-simple-clean-java-solution
    static int start = 0,maxLen = 0;
    public  static String longestPalindrome2(String s) {
        int size = s.length();
        if(size < 2){
            return s;
        }
        for(int i = 1;i<size ;i++){
            extendPalindrome(s,i,i);
            extendPalindrome(s,i,i+1);
        }
         return s.substring(start, start + maxLen);
    }

    private static void extendPalindrome(String s, int i, int j) {
        while(i >=0 && j<s.length() -1 && s.charAt(i) == s.charAt(j)){
            i--;
            j++;
        }
        if(maxLen <j+1-i ){
            maxLen = j+1-i;
            start = i;
        }
    }

    //求最长公共子序列（LCS  longest common sequences） http://www.cnblogs.com/en-heng/p/3963803.html  动态规划思想
    public static int lcs(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int c[][] = new int[len1+1][len2+1];
        for (int i = 0; i <= len1; i++) {
            for( int j = 0; j <= len2; j++) {
                if(i == 0 || j == 0) {
                    c[i][j] = 0;
                } else if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    c[i][j] = c[i-1][j-1] + 1;
                } else {
                    c[i][j] = Math.max(c[i - 1][j], c[i][j - 1]);
                }
            }
        }
        return c[len1][len2];
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

    //回溯法 https://leetcode.com/problems/letter-case-permutation/discuss/115485/Java-Easy-BFS-DFS-solution-with-explanation
    //把一字符串变换大小写并把所有情况输出
    //Examples:
    //Input: S = "a1b2"
    //Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
    private static List<String> letterCasePermutation(String s){
        List<String> lists = new ArrayList<String>();
        helper(s.toCharArray(),0,lists);
        return lists;
    }

    private static void helper(char[] chars, int pos, List<String> lists) {
        if(pos == chars.length ){
            lists.add(new String(chars));
            return;
        }
        if(chars[pos] >= '0' && chars[pos] <= '9'){
            helper(chars,pos+1,lists);
            return;
        }
        chars[pos] =  Character.toLowerCase(chars[pos]);
        helper(chars,pos+1,lists);
        chars[pos] =  Character.toUpperCase(chars[pos]);
        helper(chars,pos+1,lists);
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

    //跳格子游戏  是否可以跳到终点
    public  static boolean canJump(int[] nums) {
        return canJumpFromPosition(0, nums);
    }
    public static boolean canJumpFromPosition(int position, int[] nums) {
        if (position == nums.length - 1) {
            return true;
        }

        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
            if (canJumpFromPosition(nextPosition, nums)) {
                return true;
            }
        }

        return false;
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
