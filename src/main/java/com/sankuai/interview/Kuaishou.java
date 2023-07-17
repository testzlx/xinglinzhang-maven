package com.sankuai.interview;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Kuaishou{

    public static void main(String[] args) {
        Kuaishou kuaishou = new Kuaishou();
        kuaishou.printValV2();
    }
        private AtomicInteger i = new AtomicInteger(1);
        private Object obj = new Object();

        public void printVal(){
            Runnable a = new Runnable() {
                @Override
                public void run() {
                    while(true) {
                        synchronized (obj) {
                            if (i.get() % 2 == 1) {
                                System.out.println(i.getAndIncrement());
                                obj.notify();
                            } else {
                                try {
                                    obj.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            };
            Thread aThread = new Thread(a);

            Runnable b = new Runnable() {
                @Override
                public void run() {
                    while(true) {
                        synchronized (obj) {
                            if (i.get() % 2 == 0) {
                                System.out.println(i.getAndIncrement());
                                obj.notify();
                            } else {
                                try {
                                    obj.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    }

                }
            };

            Thread bThread = new Thread(b);
            aThread.start();
            bThread.start();
        }

    volatile int flag=0;
    public void printValV2(){

        new Thread(()-> {
            int i =1;
                while(true) {
                    if(flag == 0) {
                        System.out.println(i);
                        flag = 1;
                        i += 2;
                    }
                }
        }).start();

        new Thread(()-> {
                int i =2;
                while(true) {
                    if(flag == 1) {
                        System.out.println(i);
                        flag = 0;
                        i += 2;
                    }
                }
        }).start();


    }


        public int answer(int[] arr){
            if (arr.length <2){
                return arr.length;
            }
            int num = 1;
            int cur = arr[0];
            for(int i=1;i<arr.length;i++){
                if(cur != arr[i]){
                    cur = arr[i];
                    arr[num++] =arr[i];
                }
            }
            return num;

        }
}
