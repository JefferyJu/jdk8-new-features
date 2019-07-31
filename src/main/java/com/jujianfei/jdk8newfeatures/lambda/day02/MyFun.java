package com.jujianfei.jdk8newfeatures.lambda.day02;

/**
 * TODO
 *
 * @author Jeffery_Ju
 * @date 2019/7/31 10:56
 */
@FunctionalInterface
public interface MyFun<T> {
    Integer getValue(Integer num);
}
