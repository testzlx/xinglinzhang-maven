package com.sankuai;

/**
 * 给定一个整数n，求出1到n之间数转化为二进制后1的总个数
 * @author zhanglinxing
 *
 */

public class BinaryOneCount {

	static int count(int n){
		//arr中储存的是元素下标代表整数转化为二进制后1的个数，eg arr[1] =1 
		int arr[] = new int[n+1],sum = 0;
		for(int i = 1;i<= n;i++){
			//此处注意运算符优先级    1+3&1运算代表(1+3)&1,结果为0;应该写成1+(3&1)，结果为2
		    arr[i] = arr[i/2] + (i&1);
			sum +=arr[i];
		}
		return sum;
	}
	
	public static void main(String[] args) {
		System.out.println(count(100));
	}

}
