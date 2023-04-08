package com.sankuai.interview;

import java.util.concurrent.atomic.AtomicInteger;

public class Kuaishou{
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
