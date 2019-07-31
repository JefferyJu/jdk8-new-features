package com.jujianfei.jdk8newfeatures.lambda.day03;

/**
 * 操作字符串接口
 *
 * @author Jeffery_Ju
 * @date 2019/7/31 11:40
 */
@FunctionalInterface
public interface OperationStringInterface {
    String getValue(String string);
}
