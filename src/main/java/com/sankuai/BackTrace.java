package com.sankuai;


/**
 * 回溯算法
 */
public class BackTrace {
    public static void main(String[] args) {
        Package01 package01 = new Package01();
        package01.package01Main();
    }

    static class Package01 {
        final int totalWeight = 16;
        int[] weights = {10, 8, 5};
        int[] values = {5, 4, 1};
        int[] input = new int[3]; //x[i]=1代表物品i放入背包，0代表不放入
        int curWeight = 0;  //当前放入背包的物品总重量
        int curValue = 0;   //当前放入背包的物品总价值
        int bestValue = 0;  //最优值；当前的最大价值，初始化为0
        int bestInput[] = new int[3];       //最优解；BestX[i]=1代表物品i放入背包，0代表不放入

        /**
         * 0-1 背包问题
         */
        public void package01Main() {
            backtrack(0);
            System.out.println("BestValue:" + bestValue);
            for (int i = 0; i < 3; i++) {
                System.out.println(bestInput[i]);
            }
        }

        private void backtrack(int i) {
            if (i > 2) {
                if (curValue > bestValue) {
                    bestValue = curValue;
                }
                for (int j = 0; j < 3; j++) {
                    bestInput[j] = input[j];
                }
            } else {
                for (int k = 0; k <= 1; k++) {
                    input[i] = k;
                    if (k == 0) {
                        backtrack(i + 1);
                    } else {
                        if ((curWeight + weights[i]) <= totalWeight) {
                            curWeight += weights[i];
                            curValue += values[i];
                            backtrack(i + 1);
                            curWeight -= weights[i];
                            curValue -= values[i];
                        }

                    }

                }
            }
        }
    }
}
