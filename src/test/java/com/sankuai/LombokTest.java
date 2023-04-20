package com.sankuai;


import lombok.Data;

@Data
public class LombokTest {

    private String name;
    private int age;

    public static void main(String[] args) {
        LombokTest lombokTest = new LombokTest();
        lombokTest.setAge(123);
        System.out.println(lombokTest.getAge());
    }
}
