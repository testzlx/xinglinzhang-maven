package com.sankuai;

import java.util.Arrays;

/**
 * 
 * 
 * @author zhanglinxing
 *
 */

public class Sort {
	private int[] tests = { 3, 6, 4, 10, 8, 4, 9, 1, 2, 5, 7 };

	/**
	 * 归并排序
	 */
	private void mergeSortMain() {
		System.out.println("归并排序");
		int[] tests = { 3, 6, 4, 10, 8, 4, 9 };
		int copy[] = Arrays.copyOf(tests, tests.length);
		mergeSort(copy,0,copy.length -1);
		print(copy);
	}

	private void mergeSort(int[] copy,int low,int high){
		if(low < high){
			int middle = (low+high)/2;
			mergeSort(copy, low, middle);
			mergeSort(copy, middle+1, high);
			merge(copy,low,middle,high);
		}
	}
	private void merge(int[] copy,int low,int middle,int high){
		int[] tmpArr = new int[high+1];
		int i = low,j = middle+1, k = low;
		while(i<=middle && j<=high){
			if(copy[i] <= copy[j]){
				tmpArr[k++] = copy[i++];
			}else{
				tmpArr[k++] = copy[j++];
			}
		}
		while(i <= middle)
			tmpArr[k++] = copy[i++];
		while(j <= high)
			tmpArr[k++] = copy[j++];
		for( i = low;i<=high;i++){
			copy[i] = tmpArr[i];
		}
	}
	/**
	 * 快速排序
	 */
	private void quickSortMain() {
		System.out.println("快速排序");
		int[] tests = { 3, 6, 4, 10, 8, 4, 9 };
		int copy[] = Arrays.copyOf(tests, tests.length);
		quickSort(copy, 0, copy.length - 1);
		print(copy);
	}

	private void quickSort(int[] arr, int low, int high) {
		// int index;
		if (low < high) {
			int index = findIndex(arr, low, high);
			quickSort(arr, low, index - 1);
			quickSort(arr, index + 1, high);
		}
	}

	private int findIndex(int[] arr, int low, int high) {
		int tmp = arr[low];
		while (low < high) {
			while (arr[high] > tmp && high > low) {
				high--;
			}
			if (low < high)
				arr[low++] = arr[high];
			while (arr[low] < tmp && high > low) {
				low++;
			}
			if (low < high)
				arr[high--] = arr[low];
		}
		arr[low] = tmp;
		return low;

	}
	/**
	 * 二分查找
	 * 
	 */
	private void middleFindMain(int value){
		int copy[] = Arrays.copyOf(tests, tests.length);
		mergeSort(copy,0,copy.length -1);
		System.out.println( middleFind(copy,0,copy.length-1, value));
	}
	private boolean middleFind(int[] copy, int left, int right, int value) {
		if(left <= right){
			int middle = (left+right)/2;
			if(copy[middle] == value){
				return true;
			}else if (copy[middle] > value ) {
				return middleFind(copy, left, middle-1,value);
			}else {
				return middleFind(copy, middle+1,right,value);
			}			
		}
		return false;
	}

	/**
	 * 
	 * 简单选择排序的变体，二元排序
	 *
	 */
	private void selectSort1() {
		System.out.println("简单选择二元排序");
		// int[] tests = {3,6,4,10,8,4,9};
		int copy[] = Arrays.copyOf(tests, tests.length);
		for (int i = 0; i < copy.length / 2; i++) {
			int min = copy[i], index1 = i, index2 = copy.length - i - 1, max = copy[copy.length - i - 1];// index1
																											// 最小值的坐标，index2
																											// 最大值的坐标
			for (int j = i; j <= copy.length - i - 1; j++) {
				if (copy[j] < min) {
					min = copy[j];
					index1 = j;
				}
				if (copy[j] > max) {
					max = copy[j];
					index2 = j;
				}
			}
			/*
			 * 以下数据交换值得注意，很容易出错。。。。。。。。。。。。。。。。。
			 */
			if (index1 != i) {
				copy[i] = copy[i] + copy[index1];
				copy[index1] = copy[i] - copy[index1];
				copy[i] = copy[i] - copy[index1];
			}
			if (index2 != i) {
				copy[copy.length - i - 1] = copy[copy.length - i - 1] + copy[index2];
				copy[index2] = copy[copy.length - i - 1] - copy[index2];
				copy[copy.length - i - 1] = copy[copy.length - i - 1] - copy[index2];
			} else {
				copy[copy.length - i - 1] = copy[copy.length - i - 1] + copy[index1];
				copy[index1] = copy[copy.length - i - 1] - copy[index1];
				copy[copy.length - i - 1] = copy[copy.length - i - 1] - copy[index1];
			}
		}
		print(copy);
	}

	/**
	 * 
	 * 简单选择排序
	 *
	 */
	private void selectSort() {
		System.out.println("简单选择排序");
		// int[] tests = {3,6,4,10,8,4,9};
		int copy[] = Arrays.copyOf(tests, tests.length);
		for (int i = 0; i < copy.length - 1; i++) {
			int min = copy[i], index = i;
			for (int j = i + 1; j < copy.length; j++) {
				if (copy[j] < min) {
					min = copy[j];
					index = j;
				}
			}
			if (index != i) {
				copy[i] = copy[i] + copy[index];
				copy[index] = copy[i] - copy[index];
				copy[i] = copy[i] - copy[index];
			}
		}
		print(copy);
	}

	private void shell(int[] copy, int index) {
		for (int i = 0; i < index; i++) {
			for (int j = i; j < copy.length - index; j += index) {
				int tmp = copy[j + index], k;
				for (k = j; k >= 0; k -= index) {
					if (copy[k] > tmp) {
						copy[k + index] = copy[k];
					} else {
						break;
					}
				}
				copy[k + index] = tmp;
			}
		}
	}

	/**
	 * 希尔排序
	 */
	private void shellSort() {
		System.out.println("希尔排序");
		// int[] tests = {3,6,4,10,8,4,9};
		int copy[] = Arrays.copyOf(tests, tests.length);
		int d = copy.length / 2;
		while (d >= 1) {
			shell(copy, d);
			d = d / 2;
		}
		print(copy);
	}

	/**
	 * 插入排序
	 */
	private void insertSort() {
		System.out.println("插入排序");
		int copy[] = Arrays.copyOf(tests, tests.length);
		for (int i = 0; i < copy.length - 1; i++) {
			int tmp = copy[i + 1], j;
			for (j = i; j >= 0; j--) {
				if (copy[j] > tmp) {
					copy[j + 1] = copy[j];
				} else {
					break;
				}
			}
			copy[j + 1] = tmp;
		}
		print(copy);
	}

	/**
	 * 冒泡排序
	 * 
	 * @param args
	 */
	private void bubbleSort() {
		// int[] tests = {5,1,2,3,4};
		int copy[] = Arrays.copyOf(tests, tests.length);
		for (int i = copy.length - 1; i >= 0; i--) {
			boolean changed = false;
			for (int j = 0; j < i; j++) {
				if (copy[j] > copy[j + 1]) {
					changed = true;
					copy[j] = copy[j] + copy[j + 1];
					copy[j + 1] = copy[j] - copy[j + 1];
					copy[j] = copy[j] - copy[j + 1];
				}
			}
			if (!changed) {
				System.out.println("在i等于 " + i + "退出排序");
				break;
			}
		}
		print(copy);
	}

	/**
	 * 冒泡排序改进1
	 */
	private void bubbleSort1() {
		int[] tests = { 5, 1, 2, 3, 4 };
		int copy[] = Arrays.copyOf(tests, tests.length);
		int i = copy.length - 1;
		while (i > 0) {
			int pos = 0;
			System.out.println("i= " + i);
			for (int j = 0; j < i; j++) {
				if (copy[j] > copy[j + 1]) {
					pos = j;
					copy[j] = copy[j] + copy[j + 1];
					copy[j + 1] = copy[j] - copy[j + 1];
					copy[j] = copy[j] - copy[j + 1];
				}
			}
			i = pos;
		}
		print(copy);
	}

	/**
	 * 
	 * 冒泡排序算法2，没什么性能提升
	 */
	private void bubbleSort2() {
		// int[] tests = {5,1,2,3,4};
		int copy[] = Arrays.copyOf(tests, tests.length);
		int low = 0, high = copy.length - 1;
		while (low < high) {
			for (int j = low; j < high; j++) {
				if (copy[j] > copy[j + 1]) {
					copy[j] = copy[j] + copy[j + 1];
					copy[j + 1] = copy[j] - copy[j + 1];
					copy[j] = copy[j] - copy[j + 1];
				}
			}
			high--;
			for (int j = high; j > low; j--) {
				if (copy[j] < copy[j - 1]) {
					copy[j - 1] = copy[j - 1] + copy[j];
					copy[j] = copy[j - 1] - copy[j];
					copy[j - 1] = copy[j - 1] - copy[j];
				}
			}
			low++;
		}
		print(copy);
	}

	/**
	 * 打印数组
	 */
	private void print(int[] arrs) {
		for (int value : arrs) {
			System.out.print(value + " ");
		}
		System.out.println();
	}
	/**
	 * 计数排序
	 * 时间复杂度O(n+k),k为n数组的取值范围，空间复杂度O(3k),当k值大于nlog(n)时，不建议使用
	 * 参考 http://www.cnblogs.com/kaituorensheng/archive/2013/02/23/2923877.html
	 * 
	 */
	private void jishuSort(){
		//int copy[] = Arrays.copyOf(tests, tests.length);
		int copy[] ={7,5,3,1,6,9,9,3,3,23,24,12,7,1,6,6,6},max = 25;
		jishu(copy,max);
		//print(copy);
	}
	
	private void jishu(int copy[],int max){
		int count[] = new int[max],ranked[] = new int[max],count1[] = new int[max];
		for(int value:copy){
			++count[value];
			++count1[value];
		}
		for(int i = 1;i < max;i++){
			count[i]+=count[i-1];
		}
		for(int i=0;i<copy.length;i++){
			//int temp = count1[copy[i]];
				for(;count1[copy[i]]>0 ;count1[copy[i]]--){
					ranked[--count[copy[i]]] = copy[i];
				}
			
		}
		print(ranked);
	}
	/**
	 * 堆排序（小堆）
	 * 
	 * 
	 */
	
	private void heapSortMain(){
		System.out.println("堆排序（小堆)");
		//int[] tests = { 3, 6, 4, 10, 8, 4, 9 };
		int copy[] = Arrays.copyOf(tests, tests.length);
		heapSort(copy,copy.length);
		print(copy);
	}
	
	private void heapSort(int[] copy,int size){
		for(int i = size-1;i>0;i--){
			heapAdjust(copy,i);
			copy[0] = copy[0] + copy[i];
			copy[i] = copy[0] - copy[i];
			copy[0] = copy[0] - copy[i];
		}
	}
	private void heapAdjust(int[] copy,int index){
		for(int i = (index -1)/2;i>=0;i--){
			int tmp = 2*i+1;
			if(copy[tmp] > copy[tmp+1] && tmp+1 <= index){
				tmp +=1;
			}
			if(copy[tmp] < copy[i]){
				copy[tmp] = copy[tmp] + copy[i];
				copy[i] = copy[tmp] - copy[i];
				copy[tmp] = copy[tmp] - copy[i];
			}
		}
		
	}
	public static void main(String[] args) {
		Sort sort = new Sort();
		/*
		sort.bubbleSort();
		sort.bubbleSort1();
		sort.bubbleSort2();
		sort.insertSort();
		sort.shellSort();
		sort.selectSort();
		sort.selectSort1();
		sort.quickSortMain();
		sort.mergeSortMain();
		sort.heapSortMain();
		*/
		sort.jishuSort();
		//sort.middleFindMain(20);
	}

}
