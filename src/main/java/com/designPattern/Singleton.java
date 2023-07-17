package com.designPattern;

public class Singleton {

    private Singleton(){
    }

    private static class InnerClass{
        private static final Singleton singleton = new Singleton();
    }

    public static Singleton getInstance(){
        return InnerClass.singleton;
    }

    public static void main(String[] args) {

    }
}
