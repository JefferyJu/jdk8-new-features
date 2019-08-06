package com.jujianfei.jdk8newfeatures.stream.day07;

import lombok.NonNull;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * 重复注解与类型注解
 *
 * @author jujianfei
 * @date 2019/8/6 21:51
 */
public class AnnotationTest {




    @MyAnnotation("jujianfei")
    @MyAnnotation("wangqianying")
    public void show(@MyAnnotation("abc") String string){
        System.out.println(string);
    }


    @Test
    public void test() throws Exception {
        Method show = AnnotationTest.class.getMethod("show");
        MyAnnotation[] annotations = show.getAnnotationsByType(MyAnnotation.class);
        for (MyAnnotation annotation : annotations) {
            System.out.println(annotation.value());
        }
    }

    @Test
    public void test2() {
        show();
    }


}
