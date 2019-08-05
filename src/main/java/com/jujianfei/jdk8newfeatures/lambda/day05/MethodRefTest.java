package com.jujianfei.jdk8newfeatures.lambda.day05;

import com.jujianfei.jdk8newfeatures.lambda.day01.User;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 * 一、方法引用:若 Lambda体中的内容有方法已经实现了,我们可以使用"方法引用"
 * (可以理解为方法引用是 Lambda表达式的另外一种表观形式)
 * <p>
 * 主要有三种语法格式:
 * <p>
 * 对象::实例方法名
 * 类名::静态方法名
 * 类名::实例方法名
 * <p>
 * 注意:
 * 1、 Lambda体中调用方法的参数列表与返回值类型,要与函数式接口中抽象方法的函数列表和返回值类型保持一致!
 * 2、 若Lambda参数列表中的第一参数是实例方法的调用者,而第二个参数是实例方法的参数时,可以使用ClassName::method
 * <p>
 * 二、构造器引用
 * <p>
 * 格式：
 * <p>
 * ClassName::new
 * <p>
 * 注意：需要调用的构造器的参数列表要与的数式接口中抽象方法的参数列表保持一致！
 * <p>
 * 三、数组引用：
 * <p>
 * 格式:
 * <p>
 * Type[]::new
 *
 * @author jujianfei
 * @date 2019/7/31 19:34
 */
public class MethodRefTest {


    /**
     * 对象::实例方法名
     */
    @Test
    public void test() {
        Consumer<String> consumer = x -> System.out.println(x);

        PrintStream ps = System.out;
        Consumer<String> consumer1 = ps::println;

        Consumer<String> consumer2 = System.out::println;
    }

    @Test
    public void test2() {
        User user = new User("鲁班", 2, 3214);
        Supplier<String> supplier = () -> user.getName();
        String string = supplier.get();
        System.out.println(string);


        Supplier<Integer> supplier1 = user::getAge;
        System.out.println(supplier1.get());
    }

    /**
     * 类名::静态方法名
     */
    @Test
    public void test3() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        Comparator<Integer> comparator2 = Integer::compare;
    }

    /**
     * 类名::实例方法名
     */
    @Test
    public void test4() {
        //若Lambda参数列表中的第一参数是实例方法的调用者,而第二个参数是实例方法的参数时,可以使用ClassName::method
        BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);

        BiPredicate<String, String> biPredicate1 = String::equals;
        //参数是实例方法的调用者即可使用类名::实例方法名
        Consumer<String> stringConsumer = String::toUpperCase;

    }

    /**
     * 无参构造器引用
     */
    @Test
    public void test5() {
        Supplier<User> supplier = () -> new User();

        //构造器引用方式(无参构造器)
        //用函数式接口的抽象方法的参数列表自动匹配对应的构造器
        Supplier<User> supplier1 = User::new;
        System.out.println(supplier1.get());
    }

    /**
     * 有参构造器引用
     */
    @Test
    public void test6() {
        Function<String, User> function = name -> new User(name);

        Function<String, User> function1 = User::new;

        System.out.println(function1.apply("luban"));
        //用函数式接口的抽象方法的参数列表自动匹配对应的构造器
        BiFunction<String, Integer, User> biFunction = User::new;
    }

    /**
     * 数组引用
     */
    @Test
    public void test7() {
        Function<Integer, String[]> func = x -> new String[x];
        String[] strings = func.apply(10);
        System.out.println(strings.length);

        //数组引用

        Function<Integer, String[]> function = String[]::new;
        String[] strings1 = function.apply(20);
        System.out.println(strings1.length);

    }

}
