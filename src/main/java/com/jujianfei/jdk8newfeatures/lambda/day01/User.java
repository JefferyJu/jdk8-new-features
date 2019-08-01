package com.jujianfei.jdk8newfeatures.lambda.day01;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author jujianfei
 * @date 2019/7/30 21:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;

    private int age;

    private double salary;

    private Status status;


    public User(String name) {
        this.name = name;
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public User(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
}
