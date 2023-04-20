package com.sankuai.bit;


/**
 * 树状数组 [Binary index Tree] ===>bit 和线段树类似
 *  https://www.cnblogs.com/xenny/p/9739600.html
 *
 */
public class Main {

    int n = 10;
    int a[] = new int[n+1];
    int c[] = new int[n+1];

    public static void main(String[] args) {
        Main main = new Main();
        main.test();
    }

    void test() {
        for (int i=1;i<=n;i++) {
            a[i] =2*i-1;
            update(i,2*i-1);
        }
        int i =2,j =5;
        System.out.println(getSum(j) - getSum(i));
        update(4,5);
        System.out.println(getSum(j) - getSum(i));
    }

    int lowbit(int x) {
        return x & (-x);
    }

    void update(int i, int x) {
        while (i < n) {
            c[i] += x;
            i += lowbit(i);
        }
    }

    int getSum(int i) {
        int res = 0;
        while (i > 0) {
            res += c[i];
            i -= lowbit(i);
        }
        return res;
    }
}
