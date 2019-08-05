package com.jujianfei.jdk8newfeatures.stream.day05;

/**
 * TODO
 *
 * @author Jeffery_Ju
 * @date 2019/8/5 16:27
 */
public interface MyInterface {

    default String getName() {
        return "MyInterface";
    }

    /**
     * 接口中的静态方法
     */
    static void show() {
        System.out.println("接口中的静态方法");
    }
}
