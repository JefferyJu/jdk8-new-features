package com.jujianfei.jdk8newfeatures.stream.day02;

import com.jujianfei.jdk8newfeatures.lambda.day01.User;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Stream 练习
 *
 * @author Jeffery_Ju
 * @date 2019/8/5 9:24
 */
public class StreamExercise {


    @Test
    public void test() {
        // 1.给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？给定[1，2，3，4，5]，应该返回[1，4，9，16，25]。
        Integer[] nums = new Integer[]{1, 2, 3, 4, 5};
        Arrays.stream(nums).map(x -> x * x).forEach(System.out::println);
    }

    /**
     * 怎样用map和reduce方法数一数流中有多少个User呢？
     */
    @Test
    public void test2() {
        List<User> users = Arrays.asList(
                new User("张三", 33, 3333.33),
                new User("李四", 44, 4444.44),
                new User("王五", 55, 5555.55),
                new User("赵六", 66, 6666.66),
                new User("田七", 77, 7777.77)
        );
        Optional<Integer> reduce = users.stream().map(x -> 1).reduce(Integer::sum);
        System.out.println(reduce.get());
        System.out.println("users.stream().count() = " + users.stream().count());
    }


    @Test
    public void test3(){

    }

}
