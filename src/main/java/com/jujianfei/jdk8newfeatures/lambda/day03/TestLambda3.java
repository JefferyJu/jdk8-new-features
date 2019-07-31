package com.jujianfei.jdk8newfeatures.lambda.day03;

import com.jujianfei.jdk8newfeatures.lambda.day01.User;
import org.junit.Test;

import java.util.*;

/**
 * TODO
 *
 * @author Jeffery_Ju
 * @date 2019/7/31 11:16
 */
public class TestLambda3 {


    /**
     * 调用Collections.sort（）方法，通过定制排序比较两个 user（先按年龄比，年龄相同按工资比），使用Lambda作为参数传递。
     */
    @Test
    public void test() {

        List<User> users = Arrays.asList(
                new User("lb", 2, 999),
                new User("wqy", 18, 9999),
                new User("jjf", 18, 8888));

        Collections.sort(users, (user1, user2) -> {
            if (user1.getAge() == user2.getAge()) {
                return Double.compare(user1.getSalary(), user2.getSalary());
            } else {
                return Integer.compare(user1.getAge(), user2.getAge());
            }
        });
        users.forEach(user -> System.out.println(user));
    }

    /**
     * 2.①声明函数式接口，接口中声明抽象方法，public string getValue（String str）；
     * ②声明类Testlambda，类中编写方法使用接口作为参数，将一个字符串转换成大写，并作为方法的返回值。
     * ③再将一个字符串的第2个和第4个索引位置进行截取子串。
     */
    @Test
    public void test2() {
        String upperCaseString = stringHandler("hello", str -> str.toUpperCase());
        System.out.println(stringHandler(upperCaseString, str -> str.substring(2, 5)));
    }

    /**
     * 用于处理字符串
     *
     * @param sourceString
     * @param operationString
     * @return
     */
    public String stringHandler(String sourceString, OperationStringInterface operationString) {
        return operationString.getValue(sourceString);
    }


    /**
     * 3.①声明一个带两个泛型的函数式接口，泛型类型为<T，R>T为参数，R为返回值。
     *   ②接口中声明对应抽象方法。
     *   ③在Testlambda类中声明方法，使用接口作为参数，计算两个long型参数的和。
     *   ④再计算两个long型参数的乘积。.
     */
    @Test
    public void test3() {
        System.out.println(calculate(10L, 10L, (x, y) -> x + y));
        System.out.println(calculate(10L, 10L, (x, y) -> x * y));
    }

    /**
     * 两个Long 进行计算
     * @param num1
     * @param num2
     * @param calculator
     * @return
     */
    public Long calculate(long num1, Long num2, Calculator<Long, Long> calculator) {
        return calculator.calculate(num1, num2);
    }

}
