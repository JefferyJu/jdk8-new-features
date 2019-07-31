package com.jujianfei.jdk8newfeatures.lambda.day01;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * TODO
 *
 * @author jujianfei
 * @date 2019/7/30 21:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

    private String name;

    private int age;

    private double salary;


}
