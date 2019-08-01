package com.jujianfei.jdk8newfeatures.stream.day01;

import com.jujianfei.jdk8newfeatures.lambda.day01.User;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

/**
 * 一、Stream的三个操作步骤：
 * 1.创建 Stream
 * 2.中间操作
 * 3.终止操作（终端操作)
 *
 * @author Jeffery_Ju
 * @date 2019/8/1 15:27
 */
public class StreamTest1 {

    /**
     * 创建Stream
     */
    @Test
    public void test1() {

        //1.可以通过Collection系列集合提供的 stream() 或 parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //2. 通过Arrays 中的静态方法stream()获取数组流

        User[] users = new User[10];
        Stream<User> stream2 = Arrays.stream(users);

        //3. 通过Stream类中的静态方法of()
        Stream<String> stream3 = Stream.of("aa", "bb", "cc");

         //4. 创建无限流
        // 迭代
        Stream.iterate(0, x -> x + 2).limit(10).forEach(System.out::println);

        // 生成
        Stream.generate(() -> Math.random()).limit(5).forEach(System.out::println);


    }


}
