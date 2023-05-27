package com.daily;

import lombok.*;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class LombokTest {

    private static int add(@NonNull Integer a,@NonNull Integer b){
        return a+b;
    }

    public static void main(String[] args) {
        System.out.println(add(1,2));
        //System.out.println(add(null,4));
        Student stu = Student.builder().name("zlx").build();
        stu.setId(2);
        System.out.println(stu);
        log.error("123error");
        log.info("123");
        System.out.println(4566);
    }




}
