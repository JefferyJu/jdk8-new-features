package com.jujianfei.jdk8newfeatures.stream.day01;

import com.jujianfei.jdk8newfeatures.lambda.day01.Status;
import com.jujianfei.jdk8newfeatures.lambda.day01.User;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 一、Stream的三个操作步骤：
 * 1.创建 Stream
 * 2.中间操作
 * 3.终止操作（终端操作)
 *
 * @author jujianfei
 * @date 2019/8/1 22:04
 */
public class StreamTest3 {

    List<User> users = Arrays.asList(
            new User("张三", 33, 3333.33, Status.BUSY),
            new User("李四", 44, 4444.44, Status.VACATION),
            new User("王五", 55, 5555.55, Status.FREE),
            new User("赵六", 66, 6666.66, Status.VACATION),
            new User("田七", 77, 7777.77, Status.BUSY)
    );

    /**
     * 终止操作
     * <p>
     * 查找与匹配
     * allMatch-----检查是否匹配所有元素
     * anyMatch-----检查是否至少匹配一个元素
     * noneMatch----检查是否没有匹配所有元素
     * findFirst----返回第一个元素
     * findAny------返回当前流中的任意元素
     * count--------返回流中元素的总个数
     * max---------返回流中最大值
     * min----------返回流中最小值
     */
    @Test
    public void test1() {

    }
}
