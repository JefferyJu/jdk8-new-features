package com.jujianfei.jdk8newfeatures.stream.day02;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author Jeffery_Ju
 * @date 2019/8/5 9:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private Trader trader;
    private int year;
    private int value;
}
