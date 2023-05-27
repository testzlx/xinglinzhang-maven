package com.daily;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"age"})
public class Student{

    private int id;

    private String name;

    private int age;
}
