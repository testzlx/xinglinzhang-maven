package com.daily;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.LockSupport;

public class LockSupportExample {
    private static Thread t1, t2, t3;

    static final Object lock1 = new Object();
    static final Object lock2 = new Object();
    static final Object lock3 = new Object();


    static Semaphore s1 = new Semaphore(0); // t1 等待 t2
    static Semaphore s2 = new Semaphore(0); // t2 等待 t1
    static Semaphore s3 = new Semaphore(0); // t3 等待 t1

    public static void main(String[] args) throws Exception {
        lockSupportTest();
        Thread.sleep(2000);
        waitNotifyTest();
        Thread.sleep(2000);
        semaphoreTest();
    }

    private static void semaphoreTest(){
        System.out.println("semaphoreTest---------------------");
        Thread t1 = new Thread(() -> {
            System.out.println("A");
            s1.release(); // 唤醒 t2

            try {
                s2.acquire(); // 等待 t2 完成
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("B");
            s3.release(); // 唤醒 t3
        });

        Thread t2 = new Thread(() -> {
            try {
                s1.acquire(); // 等待 t1 唤醒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("C");
            s2.release(); // 唤醒 t1
        });

        Thread t3 = new Thread(() -> {
            try {
                s3.acquire(); // 等待 t1 唤醒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("D");
        });

        t1.start();
        t2.start();
        t3.start();
    }

    private static void lockSupportTest(){
        System.out.println("lockSupportTest---------------------");
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

    private static void waitNotifyTest(){
        System.out.println("waitNotifyTest---------------------");
        Thread t1 = new Thread(() -> {
            System.out.println("A");

            synchronized (lock2) {
                lock2.notify();  // 唤醒 t2
            }

            synchronized (lock1) {
                try {
                    lock1.wait(); // 等待 t2 完成
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("B");

            synchronized (lock3) {
                lock3.notify(); // 唤醒 t3
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                try {
                    lock2.wait(); // 等待 t1 唤醒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("C");

            synchronized (lock1) {
                lock1.notify(); // 唤醒 t1
            }
        });

        Thread t3 = new Thread(() -> {
            synchronized (lock3) {
                try {
                    lock3.wait(); // 等待 t1 唤醒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("D");
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
