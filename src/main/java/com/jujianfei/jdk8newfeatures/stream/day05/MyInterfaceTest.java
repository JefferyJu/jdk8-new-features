package com.jujianfei.jdk8newfeatures.stream.day05;

import org.junit.Test;

/**
 * TODO
 *
 * @author Jeffery_Ju
 * @date 2019/8/5 16:31
 */

public class MyInterfaceTest {

    @Test
    public  void test() {
        //如果一个父类提供了具体的实现，那么接口中具有相同名称和参数的默认方法会被忽略。
        SonClass luban = new SonClass();
        System.out.println(luban.getName());
    }

    @Test
    public void show() {
        MyInterface.show();
    }
}
