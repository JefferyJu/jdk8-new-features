package com.jujianfei.jdk8newfeatures.stream.day07;

import org.junit.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

/**
 * 重复注解与类型注解
 *
 * @author jujianfei
 * @date 2019/8/6 21:51
 */
@RestController
public class AnnotationTest {


    @MyAnnotation("jujianfei")
    @MyAnnotation("wangqianying")
    public void show(String string) {

    }


    @GetMapping("showtime/{name}")
    public void showTime(@MyAnnotation("wangqianying") String test, @PathVariable String name) {

        System.out.println(test + ":" + name);
    }


    @Test
    public void test() throws Exception {
        Method show = AnnotationTest.class.getMethod("show");
        MyAnnotation[] annotations = show.getAnnotationsByType(MyAnnotation.class);
        for (MyAnnotation annotation : annotations) {
            System.out.println(annotation.value());
        }
    }


}
