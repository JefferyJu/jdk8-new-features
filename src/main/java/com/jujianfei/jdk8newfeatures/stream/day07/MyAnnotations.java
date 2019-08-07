package com.jujianfei.jdk8newfeatures.stream.day07;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * TODO
 *
 * @author jujianfei
 * @date 2019/8/6 21:52
 */
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotations {

    MyAnnotation[] value();
}
