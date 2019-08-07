package com.jujianfei.jdk8newfeatures.stream.day07;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * TODO
 *
 * @author jujianfei
 * @date 2019/8/6 21:49
 */
@Repeatable(MyAnnotations.class)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotation {

    String value() default "jujianfei";
}
