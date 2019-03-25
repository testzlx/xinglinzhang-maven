package com.sankuai;

/**
 * 股票买卖收益最大化
 * @author zhanglinxing
 *
 */
public class Stock {
	//private int[] prices = { 11, 13, 24, 29, 7, 2, 28, 1, 4, 5 };
	private int[] prices = { 11, 10};

	public static void main(String[] args) {
		Stock stock = new Stock();
		stock.maxBenifit();

	}

	private void maxBenifit() {
		int max = 0 ,buy = prices[0];
		for(int i =1;i<prices.length;i++){
			int tmp = prices[i];
			if(tmp > buy){
				if(tmp - buy > max){
					max = tmp - buy;
				}
			}else{
				buy = tmp;
			}
		}
		System.out.println("max:"+max);
	}
}
