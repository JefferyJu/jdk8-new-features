package com.jujianfei.jdk8newfeatures.lambda.day03;

/**
 * 计算接口
 *
 * @author Jeffery_Ju
 * @date 2019/7/31 14:11
 */
@FunctionalInterface
public interface Calculator<T, R> {

    R calculate(T t1, T t2);

}

