package com.jujianfei.jdk8newfeatures.lambda.day04;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Java8内置的四大核心函数式接口
 * <p>
 * Consumer<T>:消费型接口
 * void accept(T t);
 * Supplier<T>:供给型接口
 * T get();
 * Function<T，R>:函数型接口
 * R apply(T t);
 * Predicate<T>:断言型接口
 * boolean test(T t);
 *
 * @author Jeffery_Ju
 * @date 2019/7/31 15:36
 */
public class TestLambda4 {

    /*一. 打印*/

    /**
     * Consumer<T>:消费型接口(传入参数, 进行操作, 无返回值)
     * void accept(T t);
     */
    @Test
    public void testConsumer() {
        happy(1000, t -> System.out.println(t));
    }


    public void happy(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }


    /* 二. 需求: 产生指定个数的整数, 并放入集合*/

    /**
     * Supplier<T>:供给型接口
     * T get();
     */
    @Test
    public void testSupplier() {
        List<Integer> list = getNumList(1000, () -> new Random().nextInt(100));
        for (Integer i : list) {
            System.out.println(i);
        }
    }


    public List<Integer> getNumList(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer n = supplier.get();
            list.add(n);
        }
        return list;
    }

    /*三. 需求: 用于处理字符串*/

    /**
     * Function<T，R>:函数型接口
     * <p>
     * R apply(T t);
     */
    @Test
    public void testFunc() {
        System.out.println(stringHandler(" \t\n\n\t hello_world", str -> str.trim()));
    }


    public String stringHandler(String str, Function<String, String> func) {
        return func.apply(str);
    }


    /*四. 需求：将满足条件的字符串，放入集合中*/

    /**
     * Predicate<T>:断言型接口
     * boolean test(T t);
     */
    @Test
    public void testPredicate() {
        List<String> list = Arrays.asList("luban", "wangqianying", "jujianfei");
        filterString(list, str -> str.contains("ing")).forEach(System.out::println);
    }

    public List<String> filterString(List<String> list, Predicate<String> predicate) {
        List<String> strings = new ArrayList<>();

        for (String s : list) {
            if (predicate.test(s)) {
                strings.add(s);
            }
        }
        return strings;
    }


}
