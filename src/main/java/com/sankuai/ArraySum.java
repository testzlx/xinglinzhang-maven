package com.sankuai;

/**
 * 在一个大小为n的数组中，求出其中m个数之和为s的组合
 * @author zhanglinxing
 *
 */
public class ArraySum {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n =9,m=3,s=13;
		int[] b= new int[m];
		int[] arrs = {1,2,3,4,5,6,7,8,9}; 
		fn(arrs,n,s,b,m);
	}

	private static void fn(int[] arrs, int n, int s, int[] b, int m) {
		for(int t = n-1;t >= m-1;t--){
			b[m-1] = arrs[t];
			if(m > 1){
				fn(arrs, t, s, b, m-1);
			}else{
				if(isSum(b,s)){
					print(b);
				}
			}
		}
	}

	private static void print(int[] b) {
		for(int i : b)
		System.out.print(i+" ");
		System.out.println();
	}

	private static boolean isSum(int[] b,int suM) {
		int sum = 0;
		for(int i:b){
			sum += i;
		}
		if(sum == suM)
			return true;
		return false;
	}

}
