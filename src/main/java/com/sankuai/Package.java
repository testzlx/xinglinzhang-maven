package com.sankuai;

import java.util.Arrays;

/**
 * 0/1背包问题及找零钱问题
 * @author zhanglinxing
 * @date 2017年6月6日 下午4:07:25
 */
public class Package {
	
	final static int  MAXVALUE = 100;
	/**
	 * 0/1背包问题
	 * @param items
	 * @param size
	 * @return
	 */
	 static int[][] selectObject(Item[] items,int size){
		 int max[][] = new int[items.length][size+1];
		 int i,j;
		 for(i = 1;i <= 10;i++){
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
		int size = 300;
		//print(selectObject(items, size));
		int arr[] = {3,2,5,13,96,34,21,34,67};
		chargeMoney(arr, size);
	
	}

	private static void print(int[][] result) {
		int i,j;
		for( i = 0;i<result[0].length;i++)
			for(j = 0;j< result.length;j++){
				System.out.println(result[j][i]);
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

