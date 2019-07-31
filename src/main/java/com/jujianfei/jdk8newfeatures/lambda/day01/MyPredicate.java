package com.jujianfei.jdk8newfeatures.lambda.day01;

/**
 * 策略模式
 *
 * @author Jeffery_Ju
 * @date 2019/7/31 9:11
 */
@FunctionalInterface
public interface MyPredicate<T> {

    boolean test(T t);
}
