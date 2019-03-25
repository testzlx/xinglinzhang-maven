package com.sankuai;

/**
 * 给地图染色问题(回溯法)，地图由多个区域拼凑而成（有些相邻，有些不相邻），要求相邻区域染色不能相同
 * @author zhanglinxing
 * @date 2017年7月2日 上午10:14:40
 * 
 */
/*
 * 思路：
 * 一个矩阵 各元素值代表横纵坐标两区域是否相连，值为0/1
 * 一数组：元素值存储此下标区域所所上的颜色
 * 
 * 
 * 结果：算法有缺陷，在第28行，存在area>mapSize的情况；修改方法：添加行33-36 判断条件
 */
public class MapColor {
	final static int[][] adjoins={{0,1,1,1},{1,0,0,0},{1,0,0,1},{1,0,1,0}};           //矩阵
	static int[] results=new int[adjoins.length];  //结果数组
	final static int mapSize = adjoins.length;
	final static int COLORSIZE = 4;
	static void mapColor(){
		int color = 1 ,area = 0,k ;
		results[0] = 1;
		while(area < mapSize){
			while(color <= COLORSIZE ){
				k = 0;
				while(k < area && results[k] * adjoins[area][k] != color)
					k++;
				if(k >= area){
					results[area] = color;
					area++;
					if(area >= mapSize){
						System.out.println("所有区域都添加颜色，整个任务完成!");
						return;
					}
					color = 1;
				}else{
					color++;
				}
			}
			if(color >= COLORSIZE){
				area--;
				color=results[area]+1;
			}
		}
	}

	public static void main(String[] args) {
		mapColor();

	}

}
