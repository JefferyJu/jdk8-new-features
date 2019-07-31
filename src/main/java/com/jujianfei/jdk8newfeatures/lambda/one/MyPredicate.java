package com.jujianfei.jdk8newfeatures.lambda.one;

/**
 * 策略模式
 *
 * @author Jeffery_Ju
 * @date 2019/7/31 9:11
 */
public interface MyPredicate<T> {

    boolean test(T t);
}
