package com.sankuai;

import java.util.Arrays;

/**
 * 0/1背包问题及找零钱问题
 * @author zhanglinxing
 * @date 2017年6月6日 下午4:07:25
 */
public class Package {
	
	final static int  MAXVALUE = 100; //代表从一堆零钱找不出来对应的总钱
	/**
	 * 0/1背包问题
	 * @param items
	 * @param size
	 * @return
	 */
	 static int[][] selectObject(Item[] items,int size){
		 int max[][] = new int[items.length][size+1];
		 int i,j;
		 for(i = 1;i <= size;i++){
			 for(j = 0;j < items.length;j++){
				 if(items[j].weight > i){
					 if(j != 0 )
					 max[j][i] = max[j-1][i];
				 }else{
					 if(j == 0 ){
						 max[j][i] = items[j].value;
					 }else{
					 int tmp = max[j-1][i-items[j].weight]+items[j].value;
					 max[j][i] = tmp > max[j-1][i] ? tmp:max[j-1][i];
					 }
				 }
			 }
		 }
		 return max;
	} 
	 /**
	  * 找零钱问题
	  * @param args
	  * 返回的值数组是 、数组下标是要找的前，值是选择的零钱元素的个数
	  * 个人理解：既有动态规划也有贪心算法思想（先找最大钱）
	  */
	 static int[] chargeMoney(int[] arr,int left){
		 //中间保存状态数组
		 int results[] = new int[left+1],i,j;
		 Arrays.sort(arr);
		 for(i = 1;i <= left;i++ ){
			 for(j = arr.length-1;j>=0;j--){
				 if(arr[j] > i){
					 continue;
				 }else{
					 if(results[i-arr[j]] ==MAXVALUE){
						 continue;
					 }
					 results[i] = results[i-arr[j]]+1;
					 break;
				 }
			 }
			 if(j == -1 ){
				 results[i] = MAXVALUE;
			 }
		 }
		 return results;
	 }

	public static void main(String[] args) {
		Item[] items = new Item[5];
		items[0] = new Item(2, 6);
		items[1] = new Item(2, 3);
		items[2] = new Item(6, 5);
		items[3] = new Item(5, 4);
		items[4] = new Item(4, 6);
		int size = 6;
		print(selectObject(items, size));
	//	int arr[] = {1,2,5,10,20,50};
	//	int[] result = chargeMoney(arr, size);
	//	System.out.println("result:"+result);
	
	}

	private static void print(int[][] result) {
		int i,j;
		for( i = 0;i<result[0].length;i++)
			for(j = 0;j< result.length;j++){
				System.out.println("j="+j+" i= "+ i+" value:"+result[j][i]);
			}
	}
	
	 static class Item{
		int weight;
		int value;
		protected  Item(int weight,int value){
			this.value = value;
			this.weight = weight;
		}
	}
}

