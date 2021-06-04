package com.sankuai.queue;

import java.util.*;

public class Main {
    static final String FIRST ="first";
    static final String SECOND ="second";
    static final String THIRD ="third";
    static Map<String,Integer> hmap = new HashMap<>();
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println( main.getKthMagicNumber(7));
        int[] arr ={1,2,3,2};
        System.out.println(main.maxSumMinProduct(arr));
    }

    //有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，5，7，9，15，21
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/get-kth-magic-number-lcci
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int getKthMagicNumber(int k) {
        int[] arr = new int[k+1];
        arr[0] =1;
        int p3=0,p5=0,p7=0;

        for(int i = 1;i<k;i++) {
            arr[i] = Math.min(Math.min(arr[p3]*3,arr[p5]*5),arr[p7]*7);
            if (arr[i] == arr[p3]*3) {
                ++p3;
            }
            if (arr[i] == arr[p5]*5) {
                ++p5;
            }
            if (arr[i] == arr[p7]*7) {
                ++p7;
            }
        }
        return arr[k-1];
    }


    /**
     *  https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length ==0) {
            return new int[0];
        }
        if (k ==1) {
            return nums;
        }
        int[] res = new int[nums.length+1 -k];
        Deque<Integer> queue = new LinkedList<>();
        for (int i =0;i<nums.length;i++) {
            while(!queue.isEmpty() && nums[i]>=nums[queue.peekLast()]){
                queue.removeLast();
            }
            queue.addLast(i);
            int left = i + 1 - k;
            if (queue.peekFirst() < left) {
                queue.removeFirst();
            }
            if (i+1 >= k) {
                res[i+1-k] = nums[queue.peekFirst()];
            }

        }
        return res;
    }

    //leetcode-zh-1856
    public int maxSumMinProduct(int[] nums) {
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        Arrays.fill(right,nums.length-1);
        Stack<Integer> stack = new Stack<>();
        for (int i =0;i<nums.length;i++) {
            while (!stack.empty() && nums[stack.peek()] >= nums[i] ) {
                right[stack.pop()] = i -1;
            }
            if (!stack.isEmpty()) {
                left[i] = stack.peek() +1;
            }
            stack.push(i);
        }
        int sum[] = new int[nums.length+1];
        for (int i =0 ;i<nums.length;i++) {
            sum[i+1] = sum[i] + nums[i];
        }
        long best = 0;
        for (int i = 0; i < nums.length; ++i) {
            best = Math.max(best, (sum[right[i] + 1] - sum[left[i]]) * nums[i]);
        }
        return (int)best%10000000;
    }


}
