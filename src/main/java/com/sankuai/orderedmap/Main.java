package com.sankuai.orderedmap;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] arr ={1,5,9,1,5,9};
        System.out.println(containsNearbyAlmostDuplicate(arr,2,3));
        int[] arrivals = {6,8,10,11,12,15,18,19,20,23,24,25,27,28,30,31,32,34,36,38};
        int[] loads = {15,15,5,12,8,13,2,8,10,6,4,18,9,20,4,5,14,11,4,2};
        System.out.println(busiestServers(10, arrivals, loads));
    }

    //

    /**
     * 220. 存在重复元素 III  自己写的，timeout
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        for(int i=0 ;i<nums.length;i++) {
            long cur = nums[i];
            for (int j =i-1; i-j<= k && j>=0;j--) {
                long left = nums[j];
                if (Math.abs(cur-left) <=t) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean containsNearbyAlmostDuplicateV2(int[] nums, int k, int t) {
        TreeSet<Long> treeSet = new TreeSet<>();
        for(int i=0 ;i<nums.length;i++) {
            Long val = treeSet.ceiling((long)nums[i] - (long)t);
            if(val != null && val < (long)nums[i] + (long)t) {
                return true;
            }
            treeSet.add((long)nums[i]);
            if(i >= k) {
                treeSet.remove((long) nums[i - k]);
            }
        }
        return false;
    }

    //leetcode-1606 找到处理最多请求的服务器, 部分正确， 错误原因没找到
    public static List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int[] finishTaskCount = new int[k];
        Map<Integer,Integer> map = new HashMap<>();
        int maxTime = arrival[arrival.length-1],j =0;
        for (int i =1;i<=maxTime;i++) {
            if (i == arrival[j]) {
                int t = j;
                int processorId = j % k,attemp = processorId;
                ++j;
                while (true) {
                    if (!map.containsKey(attemp)) {
                        map.put(attemp, arrival[t] + load[t]);
                        break;
                    } else if (map.get(attemp) <= i) {
                        map.put(attemp, arrival[t] + load[t]);
                        ++finishTaskCount[attemp];
                        break;
                    } else {
                        attemp = (++attemp) %k;
                    }
                    if (attemp == processorId) {
                        break;
                    }
                }
            }
        }

        List<Integer> list = new ArrayList<>();
        int max = finishTaskCount[0];
        for(Map.Entry<Integer,Integer> entry : map.entrySet()) {
            if(entry.getValue() >=maxTime) {
                ++finishTaskCount[entry.getKey()];
            }
        }
        for(int i =0;i<k;++i) {
            if(finishTaskCount[i] == max) {
                list.add(i);
            } else if(finishTaskCount[i] > max) {
                list.clear();
                max = finishTaskCount[i];
                list.add(i);
            }
        }
        return list;
    }

}
