package com.sankuai;

/**
 * 入室抢劫,每户人家组成一个环形，首尾相连
 * 题目场景描述：https://blog.csdn.net/qq_33618717/article/details/78079232
 * 
 * @author zhanglinxing
 * @date 2017年5月24日 上午9:57:09
 */
public class HouseRobber {
	static int[] arrs = { 8, 2, 2, 9, 5, 6 };

	static void maxRob() {
		int ret = 0;
		if (arrs.length == 1) {
			System.err.println(arrs[0]);
			return;
		} else if (arrs.length == 2) {
			ret = Math.max(arrs[0], arrs[1]);
			System.out.println(ret);
			return;
		}
		System.out.println(Math.max(rob(arrs, 0, arrs.length - 2), rob(arrs, 1, arrs.length - 1)));
	}
	
	
	static int rob(int arr[], int start, int end) {
		int ret = arr[start], result[] = new int[end + 1];
		result[1] = arr[start];
		result[2] = Math.max(arr[start], arr[start + 1]);
		if (end - start == 1) {
			ret = Math.max(arr[start], arr[end]);
		} else {
			for (int i = start + 2; i <= end; i++) {
				result[i] = Math.max(result[i - 2] + arr[i], result[i - 1]);
				//ret = result[i];
			}
		}
		return result[end];
	}

	public static void main(String[] args) {
		maxRob();
	}

}
