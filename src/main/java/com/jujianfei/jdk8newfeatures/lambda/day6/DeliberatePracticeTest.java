package com.jujianfei.jdk8newfeatures.lambda.day6;

import com.jujianfei.jdk8newfeatures.lambda.day01.User;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 用内置接口与方法引用重做day03练习
 *
 * @author Jeffery_Ju
 * @date 2019/8/1 10:07
 */
public class DeliberatePracticeTest {

    /*
    1.调用Collections.sort()方法，通过定制排序比较两个User（先按年龄比，年龄相同按薪水比），使用Lambda作为参数传递。
    
    2.①声明函数式接口，接口中声明抽象方法，public String getValue（String str）
      ②声明类TestLambda，类中编写方法使用接口作为参数，将一个字符串转换成大写，并作为方法的返回值。
      ③再将一个字符串的第2个和第4个索引位置进行截取子串。
      
    3.①声明一个带两个泛型的函数式接口，泛型类型为<T，R>T为参数，R为返回值
      ②接口中声明对应抽象方法
      ③在TestLambda类中声明方法，使用接口作为参数，计算两个Tlong型参数的和。
      ④再计算两个long型参数的柔积。
     */

    /**
     * 第一题
     */
    @Test
    public void test1() {
        List<User> users = Arrays.asList(
                new User("luban", 2, 55),
                new User("wangqiany", 18, 9999)
        );

        Collections.sort(users, (u1, u2) -> {
            if (u1.getAge() == u2.getAge()) {
                return Double.compare(u1.getSalary(), u2.getSalary());
            } else {
                return Integer.compare(u1.getAge(), u2.getAge());
            }
        });
        users.forEach(System.out::println);
    }

    /**
     * 第二题
     */
    @Test
    public void test2() {
        String upper = stringHandler("hello", String::toUpperCase);
        stringHandler2(2, 5, upper::substring);
    }

    public String stringHandler(String str, Function<String, String> func) {
        return func.apply(str);
    }

    public String stringHandler2(int start, int end, BiFunction<Integer, Integer, String> biFunction) {
        return biFunction.apply(start, end);
    }


    /**
     * 第三题
     */
    @Test
    public void test3() {
        System.out.println("和:" + operation(10L, 20L, Long::sum));
        System.out.println("积:" + operation(10L, 20L, (x, y) -> x * y));
    }

    public <T> long operation(long l1, long l2, BiFunction<Long, Long, Long> biFunc) {
        return biFunc.apply(l1, l2);
    }
}
