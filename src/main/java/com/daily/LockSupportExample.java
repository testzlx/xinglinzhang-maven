package com.daily;

import java.util.concurrent.locks.LockSupport;

public class LockSupportExample {
    private static Thread t1, t2, t3;

    public static void main(String[] args) throws Exception {
        t1 = new Thread(() -> {
            System.out.println("A");
            LockSupport.unpark(t2);
            LockSupport.park();
            System.out.println("B");
            LockSupport.unpark(t3);
        });

        t2 = new Thread(() -> {
            LockSupport.park();
            System.out.println("C");
            LockSupport.unpark(t1);
        });

        t3 = new Thread(() -> {
            LockSupport.park();
            System.out.println("D");
            LockSupport.unpark(t1);
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
